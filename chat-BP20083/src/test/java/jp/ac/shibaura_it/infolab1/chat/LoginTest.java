package jp.ac.shibaura_it.infolab1.chat;

import jp.ac.shibaura_it.infolab1.chat.user.*;
import jp.ac.shibaura_it.infolab1.chat.user.exception.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.PasswordUnmatchException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.UserNameUnregisteredException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {
    /*
    Test 4
    中身はTest 1 と同じ
     */
    @Test
    void loginTest() throws InvalidPasswordException, PasswordUnmatchException, UserNameUnregisteredException, UserNameDuplicateException {
        var userList = new UserList();
        var user = new User("Kenji", "password");
        userList.addUser(user);
        assertThat(userList.login("Kenji", "password")).isEqualTo(user);
    }

    /*
    Test 5
     */
    @Test
    void loginUserNameUnregisteredErrTest() throws InvalidPasswordException,  UserNameDuplicateException {
        var userList = new UserList();
        var user = new User("Kenji", "password");
        userList.addUser(user);
        UserNameUnregisteredException expected = assertThrows(UserNameUnregisteredException.class, () -> userList.login("Hiroshi", "password"));
//        System.out.println(expected.getMessage());
    }

    /*
    Test 6
     */
    @Test
    void wrongPasswordTest() throws InvalidPasswordException, UserNameDuplicateException {
        var userList = new UserList();
        var user = new User("Kenji", "password");
        userList.addUser(user);
        PasswordUnmatchException expected = assertThrows(PasswordUnmatchException.class, () -> userList.login("Kenji", "wrong"));
//        System.out.println(expected.getMessage());
    }

//    @Test
//    void logoutTest() throws InvalidPasswordException, UserNameDuplicateException {
//        User user = new User("Kenji", "password");
//        UserList userList = new UserList();
//        userList.addUser(user);
//
//    }
}
