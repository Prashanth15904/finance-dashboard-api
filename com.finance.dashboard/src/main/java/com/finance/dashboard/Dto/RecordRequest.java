package com.finance.dashboard.Dto;

import com.finance.dashboard.Entity.FinancialRecord;
import com.finance.dashboard.Entity.RecordType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RecordRequest {

    private BigDecimal amount;
    private RecordType type; // INCOME or EXPENSE
    private String category;
    private LocalDate date;
    private String notes;
    private Long userId;



    // Getters and Setters
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public RecordType getType() { return type; }
    public void setType(RecordType type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}
}