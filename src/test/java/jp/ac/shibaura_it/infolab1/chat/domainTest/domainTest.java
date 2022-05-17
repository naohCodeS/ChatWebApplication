package jp.ac.shibaura_it.infolab1.chat.domainTest;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChannelNameNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChannelNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChatTextNullException;
import jp.ac.shibaura_it.infolab1.chat.service.ChannelService;
import jp.ac.shibaura_it.infolab1.chat.service.ChatService;
import jp.ac.shibaura_it.infolab1.chat.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class domainTest {
    @Autowired
    UserService userService;

    @Autowired
    ChannelService channelService;

    @Autowired
    ChatService chatService;

    @Test
    void constructorTest() throws InvalidPasswordException {
        User noArgs = new User();
        User twoArgs = new User("userName", "password");

        assertThat(noArgs.getUsername()).isEqualTo(null);
        assertThat(noArgs.getPassword()).isEqualTo(null);

        assertThat(twoArgs.getUsername()).isEqualTo("userName");
        assertThat(twoArgs.getPassword()).isEqualTo("password");
    }

    @Test
    void userCreateTest() throws InvalidPasswordException {
        User user1 = new User("username", "password");
        User user2 = new User("username2", "password2");
        userService.create(user1);
        userService.create(user2);

        assertThat(userService.findAll().contains(user1)).isTrue();
        assertThat(userService.findAll().contains(user2)).isTrue();
    }
    @Test
    void userUpdateTest() throws InvalidPasswordException {
        userService.create(new User("username", "password"));
        userService.create(new User("username2", "password2"));
        userService.update(new User("username", "pass"));

        assertThat(userService.findOne("username").getPassword()).isEqualTo("pass");
    }

    //Autowiredのためエラーが出る可能性あり
    @Test
    void channelCreateTest() throws ChannelNameNullException {
        User user = new User("username", "password", null, null, null);
        userService.create(user);
        Channel channel = new Channel(null, "channelName", null, null);
        channelService.create(channel, user);

        assertThat(channelService.findAll().get(0)).isEqualTo(channel);

        assertThat(channel.getUsers().get(0)).isEqualTo(user);
        assertThat(user.getChannels().get(0)).isEqualTo(channel);
    }

    @Test
    void chatCreateTest() throws ChannelNullException, ChatTextNullException, ChannelNameNullException {
        User user = new User("username", "password", null, null, null);
        Channel channel = new Channel("channel");
        Chat chat = new Chat(null, null, "Hello, world!", null, null);

        userService.create(user);
        channelService.create(channel, user);
        chatService.create(chat, channel, user);

        assertThat(chatService.findAll().get(0)).isEqualTo(chat);
    }

    @Test
    void currentChannelTest() throws ChannelNameNullException {
        User user = new User("username", "password", null, null, null);
        Channel channel = new Channel("currentChannel");
        userService.create(user);
        channelService.create(channel, user);
        user.setCurrentChannel(channel);

        assertThat(user.getCurrentChannel()).isEqualTo(channel);
    }
}
