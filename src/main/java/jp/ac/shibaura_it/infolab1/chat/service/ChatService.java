package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.channel.ChannelNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.chat.ChatTextNullException;
import jp.ac.shibaura_it.infolab1.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    public Chat create(Chat chat, Channel channel, User user) throws ChannelNullException, ChatTextNullException {
        if(channel == null) throw new ChannelNullException("チャンネルが選択されていません");
        if(chat.getChatText().equals("")) throw new ChatTextNullException("テキストが入力されていません");
        chat.setChannel(channel); //チャットをチャネルへ登録
        chat.setUser(user);
        chat.setTime(LocalDateTime.now());
        if(channel.getChats() == null) channel.setChats(new ArrayList<>());
        if(user.getChats() == null) user.setChats(new ArrayList<>());
        user.getChats().add(chat);
        return chatRepository.save(chat);
    }
    public List<Chat> findAll(){return chatRepository.findAll();}
    public void delete(Integer id){chatRepository.delete(new Chat(id, null, null, null, null));}
    public void delete(Chat chat){chatRepository.delete(chat);}
}
