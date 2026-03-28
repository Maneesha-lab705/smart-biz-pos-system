package com.smartbiz.service.impl;

import com.smartbiz.dto.ExpenseDTO;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Expense;
import com.smartbiz.exception.ResourceNotFoundException;
import com.smartbiz.repository.BusinessRepository;
import com.smartbiz.repository.ExpenseRepository;
import com.smartbiz.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final BusinessRepository businessRepository;

    private ExpenseDTO toDTO(Expense e) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setExpenseId(e.getExpenseId());
        dto.setDescription(e.getDescription());
        dto.setAmount(e.getAmount());
        dto.setDate(e.getDate());
        dto.setCategory(e.getCategory());
        dto.setBusinessId(e.getBusiness().getBusinessId());
        dto.setBusinessName(e.getBusiness().getBusinessName());
        return dto;
    }

    private Expense toEntity(ExpenseDTO dto, Business business) {
        return Expense.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .date(dto.getDate() != null ? dto.getDate() : new Date())
                .category(dto.getCategory())
                .business(business)
                .build();
    }

    @Override
    public ExpenseDTO createExpense(ExpenseDTO dto) {
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new ResourceNotFoundException("Business not found: " + dto.getBusinessId()));
        return toDTO(expenseRepository.save(toEntity(dto, business)));
    }

    @Override
    public List<ExpenseDTO> getExpensesByBusiness(Long businessId) {
        return expenseRepository.findAllByBusinessBusinessId(businessId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDTO> getExpensesByDateRange(Long businessId, String from, String to) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date fromDate = sdf.parse(from);

            Date toDate = new Date();
            return expenseRepository
                    .findAllByBusinessBusinessIdAndDateBetween(businessId, fromDate, toDate)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Invalid date format, expected yyyy-MM-dd", e);
        }
    }

    @Override
    public ExpenseDTO updateExpense(Long expenseId, ExpenseDTO dto) {
        Expense existing = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found: " + expenseId));
        existing.setDescription(dto.getDescription());
        existing.setAmount(dto.getAmount());
        existing.setDate(dto.getDate());
        existing.setCategory(dto.getCategory());
        return toDTO(expenseRepository.save(existing));
    }

    @Override
    public void deleteExpense(Long expenseId) {
        if (!expenseRepository.existsById(expenseId))
            throw new ResourceNotFoundException("Expense not found: " + expenseId);
        expenseRepository.deleteById(expenseId);
    }
}
