package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    public Chat create(Chat chat, Channel channel, User user){
        chat.setChannel(channel); //チャットをチャネルへ登録
        chat.setUser(user);
        //チャネルへチャットを追加
        if(channel.getChats() == null) channel.setChats(new ArrayList<>());
        channel.getChats().add(chat);

        if(user.getChats() == null) user.setChats(new ArrayList<>());
        user.getChats().add(chat);

        System.out.println(chat);
        System.out.println(chat.getChannel());
        System.out.println(chat.getUser());

        return chatRepository.save(chat);
    }
    public List<Chat> findAll(){return chatRepository.findAll();}
    public void delete(Integer id){chatRepository.delete(new Chat(id, null, null, null, null));}
    public void delete(Chat chat){chatRepository.delete(chat);}
}
