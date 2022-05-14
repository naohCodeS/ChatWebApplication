package jp.ac.shibaura_it.infolab1.chat;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.repository.ChannelList;
import jp.ac.shibaura_it.infolab1.chat.exception.channel.ChannelNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.exception.channel.notExistChannelException;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.repository.UserList;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.PasswordUnmatchException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.UserNameUnregisteredException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChannelTest {
    @Test
    public void channelInitializedTest() throws InvalidPasswordException {
        User user = new User("Kenji", "password");
//        assertThat(user.getCurrentChannelName()).isEqualTo("Private Channel");
    }
    /*
    Test 9
     */
    @Test
    public void channelTest() throws InvalidPasswordException, UserNameDuplicateException, PasswordUnmatchException, UserNameUnregisteredException, notExistChannelException, ChannelNameDuplicateException {
        User user = new User("Kenji", "password");
        UserList userList = new UserList();
        userList.addUser(user);
        user = userList.login("Kenji", "password");
        // システムへのログイン完了
        ChannelList channelList = new ChannelList();
//        Channel channel = new Channel("Channel");
//        channelList.addChannel(channel);
//        user.channelSelect(channelList.getChannelFromChannelName("Channel"));
//        assertThat(user.getCurrentChannelName()).isEqualTo("Channel");
    }

    /*
    Test 10
     */
    @Test
    public void channelNotExistTest() throws UserNameDuplicateException, InvalidPasswordException, PasswordUnmatchException, UserNameUnregisteredException, ChannelNameDuplicateException {
        User user = new User("Kenji", "password");
        UserList userList = new UserList();
        userList.addUser(user);
        user = userList.login("Kenji", "password");

        ChannelList channelList = new ChannelList();
//        Channel channel = new Channel("Channel");
//        channelList.addChannel(channel);

        User finalUser = user;
//        notExistChannelException expected = assertThrows(notExistChannelException.class, () -> finalUser.channelSelect(channelList.getChannelFromChannelName("WrongChannel")));
//        System.out.println(expected.getMessage());
    }
}
