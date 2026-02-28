package com.smartbiz.service.impl;

import com.smartbiz.dto.DashboardDTO;
import com.smartbiz.dto.SalesReportDTO;
import com.smartbiz.entity.Sale;
import com.smartbiz.repository.*;
import com.smartbiz.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final SaleRepository        saleRepository;
    private final SaleItemRepository    saleItemRepository;
    private final CustomerRepository    customerRepository;
    private final ProductRepository     productRepository;
    private final BatchRepository       batchRepository;
    private final ExpenseRepository     expenseRepository;

    @Override
    public DashboardDTO getDashboard(Long businessId) {

        // ── Date boundaries ─────────────────────
        Calendar cal = Calendar.getInstance();

        // Today start
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date todayStart = cal.getTime();

        // Today end
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date todayEnd = cal.getTime();

        // Month start
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date monthStart = cal.getTime();

        Date now = new Date(); // month end & all-time end

        // ── Sales ─────────────────────────────
        Double salesToday   = saleRepository.getTotalRevenueByBusinessAndDateRange(businessId, todayStart, todayEnd);
        Double salesMonth   = saleRepository.getTotalRevenueByBusinessAndDateRange(businessId, monthStart, now);
        Double salesAllTime = saleRepository.getTotalRevenueByBusinessAndDateRange(
                businessId, new Date(100, 0, 1), now); // 2000-01-01

        // ── Profit ────────────────────────────
        Double costMonth     = saleRepository.getTotalCostByBusinessAndDateRange(businessId, monthStart, now);
        Double expensesMonth = expenseRepository.getTotalExpensesByBusinessAndDateRange(businessId, monthStart, now);
        Double profit        = salesMonth - costMonth;
        Double net           = profit - (expensesMonth != null ? expensesMonth : 0.0);

        // ── Inventory ─────────────────────────
        int totalProducts  = productRepository.findAllByBusinessBusinessId(businessId).size();
        int totalBatches   = batchRepository.countByProductBusinessBusinessId(businessId);
        int lowStock       = batchRepository.countByProductBusinessBusinessIdAndQtyLessThan(businessId, 10);

        // ── Customers ─────────────────────────
        int totalCustomers = customerRepository.countByBusinessBusinessId(businessId);

        // ── Top Products ──────────────────────
        List<Object[]> topRaw = saleItemRepository.findTopSellingProducts(businessId);
        List<DashboardDTO.TopProductDTO> topProducts = topRaw.stream()
                .limit(5)
                .map(row -> DashboardDTO.TopProductDTO.builder()
                        .productId(((Number) row[0]).longValue())
                        .totalQtySold(((Number) row[1]).intValue())
                        .totalRevenue(row[2] != null ? ((Number) row[2]).doubleValue() : 0.0)
                        .build())
                .collect(Collectors.toList());

        // ── Recent Sales ──────────────────────
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<DashboardDTO.RecentSaleDTO> recentSales = saleRepository
                .findTop5ByBusinessBusinessIdOrderByCreatedAtDesc(businessId)
                .stream()
                .map(s -> DashboardDTO.RecentSaleDTO.builder()
                        .saleId(s.getSaleId())
                        .invoiceNumber(s.getInvoiceNumber())
                        .customerName(s.getCustomer() != null ? s.getCustomer().getName() : "Walk-in")
                        .amount(s.getSaleItems() != null
                                ? s.getSaleItems().stream()
                                .mapToDouble(si -> si.getTotalAmount() != null
                                        ? si.getTotalAmount().doubleValue()
                                        : 0.0)
                                .sum()
                                : 0.0)
                        .createdAt(s.getCreatedAt() != null ? sdf.format(s.getCreatedAt()) : "")
                        .build())
                .collect(Collectors.toList());

        return DashboardDTO.builder()
                .totalSalesToday(salesToday)
                .totalSalesThisMonth(salesMonth)
                .totalSalesAllTime(salesAllTime)
                .totalProfitThisMonth(profit)
                .totalExpensesThisMonth(expensesMonth != null ? expensesMonth : 0.0)
                .netIncomeThisMonth(net)
                .totalProducts(totalProducts)
                .totalBatches(totalBatches)
                .lowStockCount(lowStock)
                .totalCustomers(totalCustomers)
                .newCustomersThisMonth(0)
                .topSellingProducts(topProducts)
                .recentSales(recentSales)
                .build();
    }

    @Override
    public SalesReportDTO getSalesReport(Long businessId, String from, String to) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = sdf.parse(from);
            Date toDate   = sdf.parse(to);

            // Set end of day for toDate
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            toDate = cal.getTime();

            // Totals
            Double revenue    = saleRepository.getTotalRevenueByBusinessAndDateRange(businessId, fromDate, toDate);
            Double cost       = saleRepository.getTotalCostByBusinessAndDateRange(businessId, fromDate, toDate);
            Double profit     = revenue - cost;
            Integer salesCount = saleRepository.countSalesByBusinessAndDateRange(businessId, fromDate, toDate);
            Integer itemsSold  = saleItemRepository.getTotalItemsSoldInRange(businessId, fromDate, toDate);

            // Daily breakdown
            List<Sale> sales = saleRepository.findAllByBusinessBusinessIdAndCreatedAtBetween(businessId, fromDate, toDate);
            Map<String, Double> dailyRevMap  = new LinkedHashMap<>();
            Map<String, Integer> dailyCountMap = new LinkedHashMap<>();
            SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

            for (Sale s : sales) {
                String day = sdfDay.format(s.getCreatedAt());
                Double saleTotal = s.getSaleItems() != null
                        ? s.getSaleItems().stream()
                        .mapToDouble(si -> si.getTotalAmount() != null ? si.getTotalAmount().doubleValue() : 0.0)
                        .sum()
                        : 0.0;
                dailyRevMap.merge(day, saleTotal, Double::sum);
                dailyCountMap.merge(day, 1, Integer::sum);
            }

            List<SalesReportDTO.DailySaleDTO> daily = dailyRevMap.entrySet().stream()
                    .map(e -> SalesReportDTO.DailySaleDTO.builder()
                            .date(e.getKey())
                            .revenue(e.getValue())
                            .salesCount(dailyCountMap.get(e.getKey()))
                            .build())
                    .collect(Collectors.toList());

            // Top products
            List<Object[]> topRaw = saleItemRepository.findTopSellingProducts(businessId);
            List<DashboardDTO.TopProductDTO> topProducts = topRaw.stream().limit(5)
                    .map(row -> DashboardDTO.TopProductDTO.builder()
                            .productId(((Number) row[0]).longValue())
                            .totalQtySold(((Number) row[1]).intValue())
                            .totalRevenue(row[2] != null ? ((Number) row[2]).doubleValue() : 0.0)
                            .build())
                    .collect(Collectors.toList());

            return SalesReportDTO.builder()
                    .fromDate(from)
                    .toDate(to)
                    .businessId(businessId)
                    .totalRevenue(revenue)
                    .totalCost(cost)
                    .totalProfit(profit)
                    .totalSalesCount(salesCount != null ? salesCount : 0)
                    .totalItemsSold(itemsSold != null ? itemsSold : 0)
                    .dailySales(daily)
                    .topProducts(topProducts)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Invalid date format, expected yyyy-MM-dd", e);
        }
    }
}