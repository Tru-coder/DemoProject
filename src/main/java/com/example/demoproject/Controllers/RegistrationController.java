package com.example.demoproject.Controllers;

import com.example.demoproject.Pojo.UserPojo.UserCreatingPojo;
import com.example.demoproject.Services.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserCreatingPojo pojo){
        if(userService.createUser(pojo))
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
