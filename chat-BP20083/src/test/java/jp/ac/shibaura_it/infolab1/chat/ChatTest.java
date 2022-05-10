package jp.ac.shibaura_it.infolab1.chat;

import jp.ac.shibaura_it.infolab1.chat.chat.Chat;
import jp.ac.shibaura_it.infolab1.chat.chat.exception.ChatNullException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.user.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ChatTest {
    /*
    Test 7
     */
    @Test
    void chatTest() throws InvalidPasswordException, ChatNullException {
        User user = new User("Kenji","password");
        Chat chat = new Chat(user, "Hello, world!");
        assertThat(chat.getChat()).isEqualTo("Hello, world!");
        assertThat(chat.getUser()).isEqualTo(user);
//        System.out.println(chat.getChattedTime().getTime()+"\n"+
//                           chat.getUser().getUserName());
    }

    /*
    Test 8
     */
    @Test
    void chatNullErrTest() throws InvalidPasswordException {
        User user = new User("Kenji", "password");
        ChatNullException expected = assertThrows(ChatNullException.class, ()->{
            Chat chat = new Chat(user, "");
        });
        System.out.println(expected.getMessage());
    }

    @Test
    void test(){

    }
}
