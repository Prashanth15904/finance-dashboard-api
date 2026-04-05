package com.finance.dashboard.Repository;

import com.finance.dashboard.Entity.FinancialRecord;
import com.finance.dashboard.Entity.RecordType;
import com.finance.dashboard.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord,Long> {

    List<FinancialRecord> findByUserId(Long userId);

    List<FinancialRecord> findByType(RecordType type);

    List<FinancialRecord> findByUserIdAndCategory(Long userId,String category);

    List<FinancialRecord> findTop5ByUserIdOrderByDateDesc(Long userId);

    @Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'INCOME' AND r.user.id = :userId")
    BigDecimal getTotalIncomeByUser(Long userId);

    @Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'EXPENSE' AND r.user.id = :userId")
    BigDecimal getTotalExpenseByUser(Long userId);

    @Query("SELECT r.category, SUM(r.amount) FROM FinancialRecord r WHERE r.user.id = :userId GROUP BY r.category")
    List<Object[]> getCategoryTotalsByUser(Long userId);

    @Query(" SELECT r FROM FinancialRecord r WHERE (:type IS NULL OR r.type = :type)AND (:category IS NULL OR r.category = :category)AND (:date IS NULL OR r.date = :date)AND r.user.id = :userId ")
    List<FinancialRecord> filterRecords(RecordType type, String category, LocalDate date, Long userId);

    @Query("""
SELECT FUNCTION('MONTH', r.date), SUM(r.amount)
FROM FinancialRecord r
WHERE r.user.id = :userId
GROUP BY FUNCTION('MONTH', r.date)
""")
    List<Object[]> getMonthlyTrends(Long userId);


}
