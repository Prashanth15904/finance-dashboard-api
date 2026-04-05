package com.finance.dashboard.Dto;

import com.finance.dashboard.Entity.FinancialRecord;
import com.finance.dashboard.Entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DashboardSummary {

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal balance;
    private Map<String,BigDecimal> categoryTotals;
    private List<FinancialRecord> financialRecordList;
    private Map<String, BigDecimal> monthlyTrends;

    public Map<String, BigDecimal> getMonthlyTrends() {
        return monthlyTrends;
    }

    public void setMonthlyTrends(Map<String, BigDecimal> monthlyTrends) {
        this.monthlyTrends = monthlyTrends;
    }

    public List<FinancialRecord> getFinancialRecordList() {
        return financialRecordList;
    }

    public void setFinancialRecordList(List<FinancialRecord> financialRecordList) {
        this.financialRecordList = financialRecordList;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Map<String, BigDecimal> getCategoryTotals() {
        return categoryTotals;
    }

    public void setCategoryTotals(Map<String, BigDecimal> categoryTotals) {
        this.categoryTotals = categoryTotals;
    }

    public DashboardSummary(BigDecimal totalIncome, BigDecimal totalExpense, BigDecimal balance, Map<String, BigDecimal> categoryTotals,List<FinancialRecord> financialRecordList,Map<String, BigDecimal> monthlyTrends) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
        this.categoryTotals = categoryTotals;
        this.financialRecordList = financialRecordList;
        this.monthlyTrends = monthlyTrends;
    }

    //Default Constructor
    public DashboardSummary(){};
}
