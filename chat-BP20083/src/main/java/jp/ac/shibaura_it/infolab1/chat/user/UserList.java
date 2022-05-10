package jp.ac.shibaura_it.infolab1.chat.user;

import jp.ac.shibaura_it.infolab1.chat.user.exception.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.PasswordUnmatchException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.UserNameUnregisteredException;

import java.util.ArrayList;

public class UserList {

    private ArrayList<User> userList;

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
        if(this.haveSameUserName(user.getUserName())){
            throw new UserNameDuplicateException("Duplicate User Name");
        }
        userList.add(user);
    }
    public void addUser(String userName, String password) throws UserNameDuplicateException, InvalidPasswordException {
        this.addUser(new User(userName, password));
    }

//    public ArrayList<User> getUserList(){
//        return userList;
//    }

    public User login(String userName, String password) throws PasswordUnmatchException, InvalidPasswordException, UserNameUnregisteredException {
        if(!this.haveSameUserName(userName)) throw new UserNameUnregisteredException("the username is not registered");
        User user = new User(userName, password);
        User searchedUser = this.getUserFromUserName(user.getUserName());
        if(user.equals(searchedUser))
            return searchedUser;
        else throw new PasswordUnmatchException("password is incorrect");
    }

    private User getUserFromUserName(String userName){
        for(var user: userList){
            if(user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public boolean haveSameUserName(String userName){
        for(var user: userList){
            if(user.getUserName().equals(userName)) return true;
        }
        return false;
    }
}
