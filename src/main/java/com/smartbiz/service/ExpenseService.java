package com.smartbiz.service;

import com.smartbiz.dto.ExpenseDTO;
import java.util.List;

public interface ExpenseService {
    ExpenseDTO createExpense(ExpenseDTO dto);
    List<ExpenseDTO> getExpensesByBusiness(Long businessId);
    List<ExpenseDTO> getExpensesByDateRange(Long businessId, String from, String to);
    ExpenseDTO updateExpense(Long expenseId, ExpenseDTO dto);
    void deleteExpense(Long expenseId);
}
