package jp.ac.shibaura_it.infolab1.chat.user;

import jp.ac.shibaura_it.infolab1.chat.channel.Channel;
import jp.ac.shibaura_it.infolab1.chat.channel.ChannelList;
import jp.ac.shibaura_it.infolab1.chat.chat.Chat;
import jp.ac.shibaura_it.infolab1.chat.chat.exception.ChatNullException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.user.exception.notExistChannelException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private ArrayList<Channel> channelList;
    private Channel currentChannel;

    public User(String userName, String password) throws InvalidPasswordException {
        channelList = new ArrayList<>();
        this.setAccountInfo(userName, password);
        this.currentChannel = new Channel("Private Channel");
        this.joinChannel(this.currentChannel);
    }
    public void setAccountInfo(String userName, String password) throws InvalidPasswordException {
        this.setUserName(userName);
        this.setPassword(password);
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password) throws InvalidPasswordException {
        if(password.matches("[0-9a-zA-Z]+"))
            this.password = password;
        else throw new InvalidPasswordException("Invalid password");
    }
    public String getUserName(){
        return this.userName;
    }
    private String getPassword(){
        return this.password;
    }
    public ArrayList<Channel> getChannelList(){return this.channelList;}
    public void joinChannel(Channel channel){
        this.channelList.add(channel);
    }
    public void channelSelect(Channel channel) throws notExistChannelException {
        this.currentChannel = channel;
    }
    public Channel getCurrentChannel(){return this.currentChannel;}
    // for test
    public String getCurrentChannelName(){
        return this.currentChannel.getChannelName();
    }
//    public boolean haveChannel(String channelName){
//        for(var channel : this.channelList){
//            if(channel.getChannelName().equals(channelName)) return true;
//        }
//        return false;
//    }
    public void chat(String message) throws ChatNullException {
        Chat chat = new Chat(this, message);
        this.currentChannel.addChat(chat);
    }
    @Override
    public boolean equals(Object obj){
        // なんで (User)obj.getPassword()が動くのかわからない。privateにしているのに...
        return this.userName.equals(((User)obj).getUserName()) && this.password.equals(((User)obj).getPassword());
//        return this.userName.equals(((User)obj).getUserName());
    }
    @Override
    public String toString(){
        return "username : "+this.userName+"\n";
    }
}
