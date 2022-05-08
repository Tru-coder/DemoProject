package com.example.demoproject.Models;


import com.example.demoproject.Pojo.UserPojo.UserCreatingPojo;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="usr")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private Long id;

    private String email;
    private String username;
    private String password;
    private String activationCode;

    // fetch - определяет тип параметра подгрузки: жадный, ленивый
    // collection table - создаёт табличку, которая соедина с текущей по user_id
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(UserCreatingPojo pojo){
        this.email = pojo.getEmail();
        this.username = pojo.getUsername();
        this.password = pojo.getPassword();

    }
    @PrePersist
    public void initialize() {
        if (roles == null) {
            roles = Collections.singleton(Role.USER); //Collections.singleton(Role.USER) - создаёт set с одним значением user
        }
    }
}
