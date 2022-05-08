package com.example.demoproject.Pojo.UserPojo;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatingPojo {
    private String email;
    private String username;
    private String password;
}