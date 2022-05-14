package jp.ac.shibaura_it.infolab1.chat.repository;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.PasswordUnmatchException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.UserNameUnregisteredException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserList {

    private final ArrayList<User> userList;

    public UserList() {
        this.userList = new ArrayList<>();
        try {
            User testUser = new User("TestUser", "test");
            this.userList.add(testUser);
        } catch (InvalidPasswordException e) {}
    }

    /*
    user registration
     */
    public void addUser(User user) throws UserNameDuplicateException {
        if(this.haveSameUserName(user.getUsername())){
            throw new UserNameDuplicateException("Duplicate User Name");
        }
        else {
            System.out.println("register completed");
            userList.add(user);
        }
    }
    public void addUser(String userName, String password) throws UserNameDuplicateException, InvalidPasswordException {
        this.addUser(new User(userName, password));
    }

    public User login(String userName, String password) throws PasswordUnmatchException, InvalidPasswordException, UserNameUnregisteredException {
        if(!this.haveSameUserName(userName)) throw new UserNameUnregisteredException("the username is not registered");
        User user = new User(userName, password);
        User searchedUser = this.getUserFromUserName(user.getUsername());
        if(user.equals(searchedUser)){
            System.out.println("login completed");
            return searchedUser;
        }
        else throw new PasswordUnmatchException("password is incorrect");
    }

    private User getUserFromUserName(String userName){
        for(var user: userList){
            if(user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    private boolean haveSameUserName(String userName){
        for(var user: userList){
            if(user.getUsername().equals(userName)) return true;
        }
        return false;
    }
}
