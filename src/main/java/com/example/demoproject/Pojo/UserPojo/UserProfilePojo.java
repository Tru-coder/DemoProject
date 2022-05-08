package com.example.demoproject.Pojo.UserPojo;

import com.example.demoproject.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilePojo {
    private String role;
    private String email;
    private String username;
    private String password;

    public UserProfilePojo(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.role = user.getRoles()
                .stream()//Set -> stream
                .map(Enum::name)// stream<Role> -> stream<String>
                .reduce((old, nevv) -> old + ", " + nevv) // simplify all roles in one string
                .orElse(null); // getValue from Optional

    }
}