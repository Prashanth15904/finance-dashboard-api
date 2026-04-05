package com.finance.dashboard.Exception;

import com.finance.dashboard.Dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception){

        ResponseStructure<String> response = new ResponseStructure<String>();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setData("Failure");
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoRecordFoundException(NoRecordFoundException exception){

        ResponseStructure<String> response = new ResponseStructure<String>();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setData("No Record");
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
