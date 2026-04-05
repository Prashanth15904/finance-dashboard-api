package com.finance.dashboard.Service;

import com.finance.dashboard.Dao.RecordDao;
import com.finance.dashboard.Dto.DashboardSummary;
import com.finance.dashboard.Dto.RecordRequest;
import com.finance.dashboard.Dto.ResponseStructure;
import com.finance.dashboard.Entity.FinancialRecord;
import com.finance.dashboard.Entity.RecordType;
import com.finance.dashboard.Entity.User;
import com.finance.dashboard.Exception.IdNotFoundException;
import com.finance.dashboard.Exception.NoRecordFoundException;
import com.finance.dashboard.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordDao recordDao;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ResponseStructure<FinancialRecord>> addRecord(RecordRequest recordRequest) {

        Long userId = recordRequest.getUserId();

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IdNotFoundException("User not Found")
        );

        FinancialRecord savedRecord = new FinancialRecord(
                recordRequest.getAmount(),
                recordRequest.getType(),
                recordRequest.getCategory(),
                recordRequest.getDate(),
                recordRequest.getNotes(),
                user
        );

        FinancialRecord record = recordDao.createRecord(savedRecord);

        ResponseStructure<FinancialRecord> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Record created successfully");
        response.setData(record);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<FinancialRecord>>> fetchAllRecords() {
        ResponseStructure<List<FinancialRecord>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All Records Retrived");
        response.setData(recordDao.fetchAllRecords());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<FinancialRecord>> fetchRecordsById(Long id) {
        Optional<FinancialRecord> opt = recordDao.getRecordsById(id);
        if(opt.isEmpty())
            throw new IdNotFoundException("Id not Found");

        ResponseStructure<FinancialRecord> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Record Retrived");
        response.setData(opt.get());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<FinancialRecord>>> fetchByType(RecordType type) {
        List<FinancialRecord> list = recordDao.fetchByType(type);
        if(list.isEmpty())
            throw new NoRecordFoundException("Type is Invalid");

        ResponseStructure<List<FinancialRecord>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Record retrived based on Type");
        response.setData(list);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<FinancialRecord>>> fetchByCategory(Long userId,String category) {
        List<FinancialRecord> list = recordDao.fetchByCategoryAndUserId(userId,category);
        if(list.isEmpty())
            throw new NoRecordFoundException("No Record");

        ResponseStructure<List<FinancialRecord>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Record retrived based on your category");
        response.setData(list);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<FinancialRecord>> updateRecord(RecordRequest recordRequest) {

        Long userId = recordRequest.getUserId();
        FinancialRecord financialRecord = recordDao.updateDate(userId,recordRequest);

        ResponseStructure<FinancialRecord> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Record updated");
        response.setData(financialRecord);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    public ResponseEntity<ResponseStructure<String>> deleteRecord(Long id) {

        FinancialRecord opt = recordDao.getRecordsById(id).orElseThrow(
                ()-> new IdNotFoundException("Invalid Id")
        );


        recordDao.deleteRecordsById(id);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData("Success");
        response.setMessage("Record Deleted Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<DashboardSummary>> getDashboardSummary(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("User not found"));

        BigDecimal income = recordDao.getTotalIncome(userId);
        BigDecimal expense = recordDao.getTotalExpense(userId);

        if(income == null) income = BigDecimal.ZERO;
        if(expense == null) expense = BigDecimal.ZERO;

        BigDecimal balance = income.subtract(expense);

        List<Object[]> results = recordDao.getCategoryTotals(userId);

        List<FinancialRecord> recent = recordDao.getRecentRecords(userId);

        Map<String, BigDecimal> categoryMap = new HashMap<>();

        if(results != null) {
            for(Object[] row : results) {
                String category = (String) row[0];
                BigDecimal total = (BigDecimal) row[1];
                categoryMap.put(category, total);
            }
        }

        List<Object[]> trendResults = recordDao.getMonthlyTrends(userId);

        Map<String, BigDecimal> trends = new HashMap<>();

        if(trendResults != null) {
            for(Object[] row : trendResults) {
                Integer month = (Integer) row[0];
                BigDecimal total = (BigDecimal) row[1];
                trends.put("Month " + month, total);
            }
        }

        DashboardSummary summary = new DashboardSummary();
        summary.setTotalIncome(income);
        summary.setTotalExpense(expense);
        summary.setBalance(balance);
        summary.setCategoryTotals(categoryMap);
        summary.setFinancialRecordList(recent);
        summary.setMonthlyTrends(trends);

        ResponseStructure<DashboardSummary> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Dashboard Summary Fetched");
        response.setData(summary);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<FinancialRecord>>> filterRecords(RecordType type, String category, LocalDate date, Long userId) {
        List<FinancialRecord> financialRecords = recordDao.filterRecords(type,category,date,userId);

        if(financialRecords.isEmpty())
            throw new NoRecordFoundException("No records found");

        ResponseStructure<List<FinancialRecord>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(financialRecords);
        response.setMessage("Record Fetched");

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
