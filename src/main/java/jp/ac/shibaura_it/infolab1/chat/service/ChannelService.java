package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.channel.ChannelNameNullException;
import jp.ac.shibaura_it.infolab1.chat.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {
    @Autowired
    ChannelRepository channelRepository;

    public Channel create(Channel channel, User user) throws ChannelNameNullException {
        if(channel.getChannelName().equals("")) throw new ChannelNameNullException("channel name is ''");
        if(channel.getUsers() == null) channel.setUsers(new ArrayList<>());
        channel.getUsers().add(user);
        if(user.getChannels() == null) user.setChannels(new ArrayList<>());
        user.getChannels().add(channel);
        return channelRepository.save(channel);
    }
    public List<Channel> findAll(){
        return channelRepository.findAll();
    }
    public Channel findOne(Integer id){
        return channelRepository.findById(id).get();
    }
    public Channel addUser(Channel channel, User user){
        System.out.println(":: ChannelService addUser::");
        if(!channel.getUsers().contains(user)) channel.getUsers().add(user);
        return this.update(channel);
    }
    public Channel addChat(Channel channel, Chat chat) {
        channel.getChats().add(chat);
        return this.update(channel);
    }
    public Channel deleteUser(Channel channel, User user){
        channel.getUsers().remove(user);
        return this.update(channel);
    }
    public Channel update(Channel channel){
        return channelRepository.save(channel);
    }
}
