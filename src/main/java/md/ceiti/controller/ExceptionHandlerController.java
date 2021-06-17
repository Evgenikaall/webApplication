package md.ceiti.controller;

import md.ceiti.util.exception.DepartmentNotFoundException;
import md.ceiti.util.exception.EmployeeNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<?> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        return ResponseEntity.badRequest().body(singletonMap("Department exception", ex.getMessage()));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ResponseEntity.badRequest().body(singletonMap("Employee exception", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleSQLExceptions(Exception ex){
        return ResponseEntity.badRequest().body(singletonMap("Exception", "database exception"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAnotherException(Exception ex){
        return ResponseEntity.badRequest().body(singletonMap(ex.getClass().getSimpleName(), ex.getMessage()));
    }
}
