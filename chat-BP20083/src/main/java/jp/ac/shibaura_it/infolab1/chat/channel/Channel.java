package jp.ac.shibaura_it.infolab1.chat.channel;

import jp.ac.shibaura_it.infolab1.chat.chat.Chat;
import jp.ac.shibaura_it.infolab1.chat.user.User;

import java.util.ArrayList;

public class Channel {
    private String channelName;
    private ArrayList<Chat> chatList;
    private ArrayList<User> allowedUserList;
//    private int channelID;

    public Channel(String channelName){
        this.setChannelName(channelName);
        this.chatList = new ArrayList<>();
        this.allowedUserList = new ArrayList<>();
    }
    public void setChannelName(String channelName){
        this.channelName = channelName;
    }
    public String getChannelName(){
        return this.channelName;
    }
    public ArrayList<Chat> getChatList() {
        return this.chatList;
    }
    public void addChat(Chat chat){
        this.chatList.add(chat);
    }
    public void addUser(User user){
        this.allowedUserList.add(user);
    }
}
