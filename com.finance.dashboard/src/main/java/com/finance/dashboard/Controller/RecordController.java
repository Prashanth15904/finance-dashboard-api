package com.finance.dashboard.Controller;

import com.finance.dashboard.Dto.RecordRequest;
import com.finance.dashboard.Dto.ResponseStructure;
import com.finance.dashboard.Entity.FinancialRecord;
import com.finance.dashboard.Entity.RecordType;
import com.finance.dashboard.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("/addFinancialRecords")
    public ResponseEntity<ResponseStructure<FinancialRecord>> addRecords(@RequestBody RecordRequest recordRequest){
        return recordService.addRecord(recordRequest);
    }

    @GetMapping("/fetchAllRecords")
    public ResponseEntity<ResponseStructure<List<FinancialRecord>>> fetchAllRecords(){
        return recordService.fetchAllRecords();
    }

    @GetMapping("/fetchRecords/{id}")
    public ResponseEntity<ResponseStructure<FinancialRecord>> fetchRecordsById(@PathVariable Long id){
        return recordService.fetchRecordsById(id);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ResponseStructure<List<FinancialRecord>>> fetchByType(@PathVariable RecordType type) {
        return recordService.fetchByType(type);
    }

    @GetMapping("/category/{userId}/{category}")
    public ResponseEntity<ResponseStructure<List<FinancialRecord>>> fetchByCategory(@PathVariable Long userId,@PathVariable String category ){
        return recordService.fetchByCategory(userId,category);
    }

    @PutMapping("/updateRecord")
    public ResponseEntity<ResponseStructure<FinancialRecord>> updateRecord(@RequestBody RecordRequest recordRequest){
        return recordService.updateRecord(recordRequest);
    }

    @DeleteMapping("/deleteRecord/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteRecord(@PathVariable Long id){
        return recordService.deleteRecord(id);
    }

    @GetMapping("/filterRecords")
    public ResponseEntity<ResponseStructure<List<FinancialRecord>>> filterRecords(@RequestParam RecordType type, @RequestParam String category, @RequestParam LocalDate date, @RequestParam Long userId){
        return recordService.filterRecords(type,category,date,userId);
    }


}
