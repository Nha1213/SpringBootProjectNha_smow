package com.nha.nha_smos.Controller;

import com.nha.nha_smos.DTO.User.LoginRequest;
import com.nha.nha_smos.DTO.User.LoginResponse;
import com.nha.nha_smos.DTO.User.RegisterRequest;
import com.nha.nha_smos.DTO.User.RegisterResponse;
import com.nha.nha_smos.Model.UserModel;
import com.nha.nha_smos.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

//    private UserController (UserService userService){
//        this.userService = userService;
//    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest req){
        return ResponseEntity.ok(userService.registerUser(req));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req){
        return ResponseEntity.ok(userService.login(req));
    }
}
