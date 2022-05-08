package com.example.demoproject.Controllers;

import com.example.demoproject.Pojo.UserPojo.UserSignInPojo;
import com.example.demoproject.Services.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<HttpStatus> signIn (@RequestBody UserSignInPojo pojo){
        if(userService.SignIn(pojo)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}