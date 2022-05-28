package com.example.demoproject.Services.UserService;

import com.example.demoproject.Models.User;
import com.example.demoproject.Pojo.UserPojo.UserCreatingPojo;
import com.example.demoproject.Pojo.UserPojo.UserUpdatingPojo;
import com.example.demoproject.Repositories.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasicUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void saveUserWithEncode(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    /**
     * Return List of Users who exists in database
     *
     * @return List<User>
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Return user from database searching by username
     *
     * @param username {@link User#username}
     * @return null if User was not found else User
     */
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * Return user from database searching by username
     *
     * @param id {@link User#id}
     * @return null if User was not found else User
     */
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Return boolean expression of user existing in database
     * searching by {@link User#username}
     *
     * @param username {@link User#username}
     * @return true if users exists, else false
     */
    public boolean IsUserExists(String username) {
        return this.getUser(username) != null;
    }

    /**
     * Return boolean expression of user existing in database
     * searching by {@link User#id}
     *
     * @param id {@link User#id}
     * @return true if users exists, else false
     */
    public boolean IsUserExists(Long id) {
        return this.getUser(id) != null;
    }

    /**
     * Return boolean expression of user not existing in database
     * searching by {@link User#username}
     *
     * @param username {@link User#username}
     * @return true if user not exists, else false
     */
    public boolean IsNotUserExists(String username) {
        return !this.IsUserExists(username);
    }

    /**
     * Return boolean expression of user not existing in database
     * searching by {@link User#id}
     *
     * @param id {@link User#id}
     * @return true if user not exists, else false
     */

    public boolean IsNotUserExists(Long id) {
        return !this.IsUserExists(id);
    }

    /**
     * Delete User located in database
     *
     * @param user not null {@link User}
     */
    public void deleteUser(@NotNull User user) {
        userRepository.delete(user);
    }

    /**
     * Delete all  Users ({@link User}) in database
     */
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    /**
     * Create {@link User} using data from {@link UserCreatingPojo}
     * then save it to database
     *
     * @param user {@link User}
     */
    public void createUser(User user) {
        saveUserWithEncode(user);
    }

    /**
     * Updating User {@link User} using data from {@link UserUpdatingPojo}
     *
     * @param userId {@link User#id}
     */
    public void updateUser(Long userId, UserUpdatingPojo pojo) {
        User user = getUser(userId);
        user.setEmail(pojo.getEmail());
        user.setPassword(pojo.getPassword());
        user.setUsername(pojo.getUsername());
        saveUserWithEncode(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.demoproject.Models.User user = getUser(username);
        if (user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }
        else{
            log.info("User found in database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role-> authorities.add
                (new SimpleGrantedAuthority(role.getAuthority())));
        return  new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), authorities);
    }
}