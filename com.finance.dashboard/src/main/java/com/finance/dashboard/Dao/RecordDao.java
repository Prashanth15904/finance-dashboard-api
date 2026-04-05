package com.finance.dashboard.Dao;

import com.finance.dashboard.Dto.RecordRequest;
import com.finance.dashboard.Entity.FinancialRecord;
import com.finance.dashboard.Entity.RecordType;
import com.finance.dashboard.Entity.User;
import com.finance.dashboard.Repository.FinancialRecordRepository;
import com.finance.dashboard.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class RecordDao {

    @Autowired
    private FinancialRecordRepository financialRecordRepository;

    public FinancialRecord createRecord(FinancialRecord financialRecord) {
        return financialRecordRepository.save(financialRecord);
    }

    public Optional<FinancialRecord> getRecordsById(Long id) {
        return financialRecordRepository.findById(id);
    }

    public List<FinancialRecord> fetchAllRecords() {
        return financialRecordRepository.findAll();
    }

    public List<FinancialRecord> fetchByType(RecordType type) {
        return financialRecordRepository.findByType(type);
    }

    public List<FinancialRecord> fetchByCategoryAndUserId(Long userId, String category) {
        return financialRecordRepository.findByUserIdAndCategory(userId,category);
    }

    public FinancialRecord updateDate(Long userId, RecordRequest recordRequest) {

        User user = financialRecordRepository.findById(userId).orElseThrow().getUser();

        FinancialRecord record = new FinancialRecord(
                recordRequest.getAmount(),
                recordRequest.getType(),
                recordRequest.getCategory(),
                recordRequest.getDate(),
                recordRequest.getNotes(),
                user
        );
        return financialRecordRepository.save(record);
    }

    public void deleteRecordsById(Long id) {

        FinancialRecord record = financialRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        financialRecordRepository.delete(record);
    }

    public BigDecimal getTotalIncome(Long userId) {
        return financialRecordRepository.getTotalIncomeByUser(userId);
    }

    public BigDecimal getTotalExpense(Long userId) {
        return financialRecordRepository.getTotalExpenseByUser(userId);
    }

    public List<Object[]> getCategoryTotals(Long userId) {
        return financialRecordRepository.getCategoryTotalsByUser(userId);
    }

    public List<FinancialRecord> filterRecords(RecordType type, String category, LocalDate date, Long userId) {
        return financialRecordRepository.filterRecords(type,category,date,userId);
    }

    public List<FinancialRecord> getRecentRecords(Long userId) {
        return financialRecordRepository.findTop5ByUserIdOrderByDateDesc(userId);
    }

    public List<Object[]> getMonthlyTrends(Long userId) {
        return financialRecordRepository.getMonthlyTrends(userId);
    }
}