package jp.ac.shibaura_it.infolab1.chat;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.chat.ChatNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChannelNameNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChannelNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChatTextNullException;
import jp.ac.shibaura_it.infolab1.chat.service.ChannelService;
import jp.ac.shibaura_it.infolab1.chat.service.ChatService;
import jp.ac.shibaura_it.infolab1.chat.service.UserService;
import jp.ac.shibaura_it.infolab1.chat.web.RegisterForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UnitTest {
    @Autowired
    UserService userService;

    @Autowired
    ChannelService channelService;

    @Autowired
    ChatService chatService;

    @Test
    void userRegistrationTest() throws InvalidPasswordException, UserNameDuplicateException {
        User user = new User(); user.setUsername("username"); user.setPassword("password");
        userService.register(user.getUsername(), user.getPassword());

        assertThat(userService.findOne("username")).isEqualTo(user);
    }

    @Test
    void usernameDuplicatedExceptionTest() throws UserNameDuplicateException {
        try {
            userService.register("username", "password");
        } catch (UserNameDuplicateException e) {
            throw new RuntimeException(e);
        }

        assertThrows(UserNameDuplicateException.class, ()-> userService.register("username", "password2"));
    }

    @Test
    void InvalidPasswordExceptionTest(){
        //spring bootを用いた状態でないと動かないため手動で行う
//        RegisterForm registerForm = new RegisterForm();
//        registerForm.setPassword("/");
    }

    @Test
    void loginTest() throws UserNameDuplicateException {
        //SpringSecurityを用いているため手動で行う
    }

    @Test
    void chatTest() throws InvalidPasswordException, UserNameDuplicateException, ChannelNameNullException, ChatTextNullException, ChannelNullException {
        User user = userService.register("username", "password");
        Channel channel = new Channel();
        channel.setChannelName("channel");
        channelService.create(channel, user);
        Chat chat = new Chat(); chat.setChatText("chat text");
        chatService.create(chat, channel, user);

        assertThat(chatService.findAll().get(0)).isEqualTo(chat);
    }

    @Test
    void chatTextNullExceptionTest() throws UserNameDuplicateException, ChannelNameNullException {
        User user = userService.register("username", "password");
        Channel channel = new Channel();
        channel.setChannelName("channel");
        channelService.create(channel, user);
        Chat chat = new Chat(); chat.setChatText("");

        assertThrows(ChatTextNullException.class, ()->chatService.create(chat, channel, user));
    }

    @Test
    void channelTest() throws UserNameDuplicateException, ChannelNameNullException {
        User user = userService.register("username", "password");
        Channel channel = new Channel();
        channel.setChannelName("channel");
        channelService.create(channel, user);

        Integer id = channel.getId();
        assertThat(channelService.findOne(id)).isEqualTo(channel);
    }

    @Test
    void channelNullExceptionTest() throws UserNameDuplicateException, ChannelNameNullException {
        User user = userService.register("username", "password");
        Channel channel = new Channel();
        channel.setChannelName("channel");
        channelService.create(channel, user);

        assertThrows(java.util.NoSuchElementException.class, ()->channelService.findOne(2));
    }
}
