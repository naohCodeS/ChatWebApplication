package jp.ac.shibaura_it.infolab1.chat.jpaTest;

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

@SpringBootTest
public class japTest {
    @Autowired
    UserService userService;
    @Autowired
    ChannelService channelService;
    @Autowired
    ChatService chatService;

    @Test
    void test() throws InvalidPasswordException, ChannelNullException, ChatTextNullException, ChannelNameNullException {

        User user = new User(); user.setUsername(String.valueOf(1)); user.setPassword(String.valueOf(1));

        for(int i=0; i<100; i++){

            Channel channel = new Channel(); channel.setChannelName(String.valueOf(i));
            Chat chat = new Chat(); chat.setChatText(String.valueOf(i));

            userService.create(user);
            channelService.create(channel, user);
            chatService.create(chat, channel, user);
        }

        System.out.println(user.getChannels());

        user.setCurrentChannel(channelService.findOne(1));
        System.out.println(user.getCurrentChannel());

        Channel channel = user.getCurrentChannel();
        channel.setChannelName("re");
        channelService.update(channel);

        System.out.println(user.getCurrentChannel());
        System.out.println(channelService.findOne(1));

        System.out.println(user.getChannels());
        System.out.println(channelService.findAll());
    }
}
