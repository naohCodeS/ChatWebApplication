package jp.ac.shibaura_it.infolab1.chat.channel;


import jp.ac.shibaura_it.infolab1.chat.user.exception.notExistChannelException;

import java.util.ArrayList;

public class ChannelList {
    private ArrayList<Channel> channelList;

    public ChannelList(){
        this.channelList = new ArrayList<>();
        Channel testChannel = new Channel("Test");
        this.channelList.add(testChannel);
    }
    public void addChannel(Channel channel){
        this.channelList.add(channel);
    }
    public Channel getChannelFromChannelName(String channelName) throws notExistChannelException {
        for(var channel : this.channelList){
            if(channel.getChannelName().equals(channelName)) return channel;
        }
        throw new notExistChannelException("not exist such channel");
    }
}
