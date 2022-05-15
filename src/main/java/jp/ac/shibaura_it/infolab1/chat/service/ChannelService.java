package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {
    @Autowired
    ChannelRepository channelRepository;

    public Channel create(Channel channel, User user){
        //チャネルへのユーザ追加
        if(channel.getUsers() == null) {
            channel.setUsers(new ArrayList<>());
        }

        channel.getUsers().add(user);

        //ユーザへのチャネル追加
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

    public Channel update(Channel channel, User user){
        if(user.getChannels() == null) user.setChannels(new ArrayList<>());
        user.getChannels().add(channel);
        return channelRepository.save(channel);
    }
}
