package com.nha.nha_smos.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Setter
@Getter
//@AllArgsConstructor
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private Integer code;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse(Boolean success, String message, Integer code, T data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

//    create
    public static <T> ApiResponse<T> create (T Data) {
       return new ApiResponse<>(true,"Create Success", HttpStatus.CREATED.value(), Data);
    }

    public static <T> ApiResponse<T> create (T Data, String message) {
        return new ApiResponse<>(true, message, HttpStatus.CREATED.value(), Data);
    }

//    update
//   using list, listOne, update, remove
    public static <T> ApiResponse<T> ok (T Data, String message) {
        return new ApiResponse<>(true, message, HttpStatus.OK.value(), Data);
    }
    public static <T> ApiResponse<T> ok (T Data) {
        return new ApiResponse<>(true, "success", HttpStatus.OK.value(), Data);
    }

//   using error
    public static <T> ApiResponse<T> error (T Data, String message, Integer status) {
        return new ApiResponse<>(false, message, status, Data);
    }

    public static <T> ApiResponse<T> error (T Data, String message, HttpStatus status) {
        return new ApiResponse<>(false, message, status.value(), Data);
    }

}
