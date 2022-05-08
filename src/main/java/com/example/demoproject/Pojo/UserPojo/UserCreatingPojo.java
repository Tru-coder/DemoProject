package com.example.demoproject.Pojo.UserPojo;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatingPojo {
    private String email;
    private String username;
    private String password;
}
