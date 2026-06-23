package com.nha.nha_smos.Exception;

import com.nha.nha_smos.Util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    // message error when process
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exception(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((item) -> {
//                    item.getField(),
//                    item.getDefaultMessage(),
                    errors.put(item.getField(),item.getDefaultMessage());
                }
        );
        Map<String, Object> res = new HashMap<>();
        res.put(("success"), false);
        res.put("code", HttpStatus.BAD_REQUEST.value());
        res.put("message", "Bad Request");
        res.put("timestamp", LocalDate.now());
        res.put("error", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

//    handle general exception
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleException(Exception exception) {
//        Map<String, Object> res = new HashMap<>();
//        res.put("success", false);
//        res.put("code", 500);
//        //res.put("message", exception.getMessage());
//        res.put("message", "Something went wrong");
//        res.put("timestamp", LocalDateTime.now());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
//    }
//    message error auto catch error 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
               ApiResponse.error(null,"Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR)
       );
    }
}
