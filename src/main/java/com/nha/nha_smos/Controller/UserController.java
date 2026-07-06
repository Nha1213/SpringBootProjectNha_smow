package com.nha.nha_smos.Controller;

import com.nha.nha_smos.DTO.RegisterRequest;
import com.nha.nha_smos.Model.UserModel;
import com.nha.nha_smos.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/auth")
    public ResponseEntity<UserModel>  register(@Valid @RequestBody RegisterRequest req){
        UserModel user = userService.registerUser(req);
        return ResponseEntity.ok(user);
    }

}
