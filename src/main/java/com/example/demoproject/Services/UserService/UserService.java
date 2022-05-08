package com.example.demoproject.Services.UserService;

import com.example.demoproject.Models.User;
import com.example.demoproject.Pojo.UserPojo.UserCreatingPojo;
import com.example.demoproject.Pojo.UserPojo.UserProfilePojo;
import com.example.demoproject.Pojo.UserPojo.UserSignInPojo;
import com.example.demoproject.Pojo.UserPojo.UserUpdatingPojo;
import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final BasicUserService basicUserService;

    /**
     * Return all Users in database
     *
     * @return List of {@link User}
     */
    private List<User> getAllUsers() {
        return basicUserService.getAllUsers();
    }

    /**
     * Get {@link User} from database then convert him
     * to return {@link UserProfilePojo}
     *
     * @param id {@link User#id}
     * @return {@link UserProfilePojo}
     */
    public UserProfilePojo getUserProfilePojo(Long id) {
        return new UserProfilePojo(basicUserService.getUser(id));
    }

    /**
     * Searching {@link User} by his {@link User#id} if he is not exists return false
     * then checking if the name is available searching by {@link User#username}
     * if the name was unavailable return false
     * else update fields in User using data from {@link UserCreatingPojo} and return true
     *
     * @param userId {@link User#id}
     * @param pojo   {@link UserUpdatingPojo}
     * @return true if updating has been else false
     */
    public boolean updateUser(Long userId, @NotNull UserUpdatingPojo pojo) {
        if (!this.IsNotNullFieldsUserCreatingPojo(pojo)){
            return false;
        }
        if (basicUserService.IsUserExists(pojo.getUsername())) {
            return false; // user with such name already exists
        }
        if (basicUserService.IsNotUserExists(userId)) {
            return false; // user with such id not exists
        }
        //todo: rewrite in one argument cost to do it?
        basicUserService.updateUser(userId, pojo);
        return true; // user has changed
    }


    /**
     * @param pojo {@link UserCreatingPojo}
     * @return false if user already exists with name from {@link UserCreatingPojo#username}
     * else True
     */
    public boolean createUser(@NotNull  UserCreatingPojo pojo) {
        if (basicUserService.IsUserExists(pojo.getUsername())) {
            return false; // user with this name was already created
        }
        if (!this.IsNotNullFieldsUserCreatingPojo(pojo)){
            return  false;
        }
        basicUserService.createUser(new User(pojo));
        return true; // user created

    }

    /**
     * Return true if password and username exists in bd for certain user
     * Searching is organised by username
     *
     * @param pojo with {@link User#password}, {@link User#username}
     * @return boolean
     */
    public boolean SignIn(@NotNull UserSignInPojo pojo) {
        if (basicUserService.IsNotUserExists(pojo.getUsername())) {
            return false; // user not found in DB
        }
        return basicUserService
                .getUser(pojo.getUsername())
                .getPassword()
                .equals(pojo.getPassword());
    }

    /**
     * Delete {@link User}User with such id
     *
     * @param id {@link User#id}
     * @return true if deleting has been else false
     */
    public boolean deleteUser(@NotNull Long id) {
        if (basicUserService.IsNotUserExists(id)) {
            return false;
        }
        basicUserService.deleteUser(basicUserService.getUser(id));
        return true;
    }


    /**
     * Return all Users in database
     * in view of {@link  UserProfilePojo}
     * @return List of {@link UserProfilePojo}
     */
    public List<UserProfilePojo>  getAllUsersProfile (){
       return  this.getAllUsers()
                .stream()
                .map(UserProfilePojo::new)
                .collect(Collectors.toList());
    }

    private boolean IsNotNullFieldsUserCreatingPojo (UserCreatingPojo pojo){
        if (pojo.getUsername() == null){
            return false;
        }
        if (pojo.getEmail() == null){
            return false;
        }
        if (pojo.getPassword() == null){
            return false;
        }
        return true;
    }
    private boolean IsNotNullFieldsUserCreatingPojo (UserUpdatingPojo pojo){
        if (pojo.getUsername() == null){
            return false;
        }
        if (pojo.getEmail() == null){
            return false;
        }
        if (pojo.getPassword() == null){
            return false;
        }
        return true;
    }
}
