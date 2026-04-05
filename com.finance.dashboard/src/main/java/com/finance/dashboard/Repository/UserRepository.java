package com.finance.dashboard.Repository;

import com.finance.dashboard.Entity.FinancialRecord;
import com.finance.dashboard.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    void deleteById(Long id);


}
