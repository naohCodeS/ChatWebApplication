package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.user.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChannelNameNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChannelNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChatTextNullException;
import org.springframework.beans.factory.annotation.Autowired;

//なし

public class ChatApplicationService {
    @Autowired
    UserService userService;
    @Autowired
    ChannelService channelService;
    @Autowired
    ChatService chatService;

    /*
    register
    chat
    selectChannel
    createChannel
     */

    public User registerUser(String username, String password) throws UserNameDuplicateException {
        return userService.register(username, password);
    }
    public void chat(String text, Channel channel, User user) throws ChannelNullException, ChatTextNullException {
        Chat chat = new Chat(); chat.setChatText(text);
        chatService.create(chat, channel, user);
        channelService.addChat(channel, chat);
    }
    public void selectChannel(Channel channel, User user){
        if(channel == null) return;
        if(user.getCurrentChannel() == channel) return;
        userService.addChannel(user, channel);
        channelService.addUser(channel, user);
        userService.changeCurrentChannel(user, channel);
    }
    public void createChannel(Channel channel, User user) throws ChannelNameNullException {
        channelService.create(channel, user);
    }
}
