package jp.ac.shibaura_it.infolab1.chat.repository;


import jp.ac.shibaura_it.infolab1.chat.exception.channel.ChannelNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.exception.channel.notExistChannelException;
import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ChannelList {
    private ArrayList<Channel> channelList;

    public ChannelList(){
        this.channelList = new ArrayList<>();
//        Channel testChannel = new Channel("Test");
//        this.channelList.add(testChannel);
    }
    public ArrayList<Channel> getChannelList(){
        return this.channelList;
    }
    public void addChannel(Channel channel) throws ChannelNameDuplicateException {
        if(this.channelList.contains(channel)) throw new ChannelNameDuplicateException("the channel name has already be exist");
        else {
            System.out.println(channel.getChannelName() + " was correctly added");
            this.channelList.add(channel);
        }
    }
    public Channel getChannelFromChannelName(String channelName) throws notExistChannelException {
        for(var channel : this.channelList){
            if(channel.getChannelName().equals(channelName)) return channel;
        }
        throw new notExistChannelException("not exist such channel");
    }
}
