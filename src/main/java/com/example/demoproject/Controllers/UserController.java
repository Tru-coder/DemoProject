package com.example.demoproject.Controllers;

import com.example.demoproject.Pojo.UserPojo.UserProfilePojo;
import com.example.demoproject.Pojo.UserPojo.UserSignInPojo;
import com.example.demoproject.Pojo.UserPojo.UserUpdatingPojo;
import com.example.demoproject.Services.UserService.UserService;
import com.example.demoproject.authentification.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;


    @GetMapping("/list")
    public List<UserProfilePojo> getAllUsers(){
        return userService.getAllUsersProfile();
    }
    @GetMapping("/{id}")
    public UserProfilePojo getUser(@PathVariable Long id){
        return userService.getUserProfilePojo(id);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable Long id, @RequestBody UserUpdatingPojo pojo){
        if (userService.updateUser(id, pojo))
            return new ResponseEntity<>(HttpStatus.OK); // has changed
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED); // has not changed

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/refreshtoken")
    public void refreshtoken(HttpServletRequest request, HttpServletResponse response){
        authService.refreshtoken(request, response);
    }

}
