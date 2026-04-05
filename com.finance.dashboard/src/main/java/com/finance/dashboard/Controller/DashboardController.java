package com.finance.dashboard.Controller;
import com.finance.dashboard.Dto.DashboardSummary;
import com.finance.dashboard.Dto.ResponseStructure;
import com.finance.dashboard.Repository.FinancialRecordRepository;
import com.finance.dashboard.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/dashboard/summary/{userId}")
    public ResponseEntity<ResponseStructure<DashboardSummary>> getSummary(@PathVariable Long userId) {
        return recordService.getDashboardSummary(userId);
    }

}