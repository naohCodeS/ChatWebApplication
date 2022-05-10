package jp.ac.shibaura_it.infolab1.chat;

import jp.ac.shibaura_it.infolab1.chat.user.*;
import jp.ac.shibaura_it.infolab1.chat.user.exception.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.PasswordUnmatchException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.UserNameUnregisteredException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

//    @Test
//    void makingUserTest() throws InvalidPasswordException {
//        var user1 = new User("Kenji", "password");
//        assertThat(user1.getUserName()).isEqualTo("Kenji");
//        assertThat(user1.getPassword()).isEqualTo("password");
//        assertThat(user1).isEqualTo(new User("Kenji", "password"));
//    }

    /*
    Test 1
     */
    @Test
    void userRegistrationTest() throws InvalidPasswordException, UserNameDuplicateException, PasswordUnmatchException, UserNameUnregisteredException {
        var user = new User("Kenji", "password");
        UserList userList = new UserList();
        userList.addUser(user);
        User registeredUser = userList.login("Kenji","password");
//        System.out.println("UserName : "+ registeredUser.getUserName()+"\n"+
//                           "Password : "+registeredUser.getPassword());
        assertThat(registeredUser).isEqualTo(user);
    }

    /*
    Test 2
     */
    @Test
    void duplicateUserNameExceptionTest() throws InvalidPasswordException {
        var user = new User("Kenji", "password");
        UserList userList = new UserList();
        try {
            userList.addUser(user);
        } catch (UserNameDuplicateException e) {
            System.out.println("Error : "+e.getMessage());
        }

        var user2 = new User("Kenji", "password2");
        UserNameDuplicateException expected = assertThrows(UserNameDuplicateException.class, ()->{
           userList.addUser(user2);
        });
//        System.out.println(expected.getMessage());
    }

    /*
    Test 3
     */
    @Test
    void InvalidPasswordExceptionTest() {
        InvalidPasswordException expected = assertThrows(InvalidPasswordException.class, () -> {
            User user = new User("Kenji", "*123");
        });
//        System.out.println(expected.getMessage());
    }
//
//    @Test
//    void userListTest() throws UserNameDuplicateException, InvalidPasswordException, PasswordUnmatchException, UserNameUnregisteredException {
//        var user1 = new User("Kenji", "password");
//        var user2 = new User("Takashi", "password2");
//        UserList userList = new UserList();
//        userList.addUser(user1);
//        userList.addUser(user2);
//        assertThat(userList.login("Kenji", "password")).isEqualTo(user1);
//        assertThat(userList.login("Takashi", "password2")).isEqualTo(user2);
//    }
}
