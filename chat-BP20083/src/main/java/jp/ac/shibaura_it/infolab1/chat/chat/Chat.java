package jp.ac.shibaura_it.infolab1.chat.chat;

import jp.ac.shibaura_it.infolab1.chat.chat.exception.ChatNullException;
import jp.ac.shibaura_it.infolab1.chat.user.User;

import java.util.Calendar;

public class Chat {
    private User user;
    private String chat;
    private Calendar calendar;

    public Chat(User user, String chat) throws ChatNullException {
        calendar = Calendar.getInstance();
        this.setUser(user);
        this.setChat(chat);
    }
    private void setUser(User user){
        this.user = user;
    }
    private void setChat(String chat) throws ChatNullException {
        if(chat.isEmpty()) throw new ChatNullException("Chat is Null");
        this.chat = chat;
    }
    public User getUser(){
        return this.user;
    }
    public String getChat(){
        return this.chat;
    }
    public String getChattedTime(){
        String month = String.valueOf(this.calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(this.calendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(this.calendar.get(Calendar.HOUR));
        String minute = String.valueOf(this.calendar.get(Calendar.MINUTE));
        return month+"/"+day+"/"+hour+":"+minute;
    }
//    @Override
//    public String toString(){
//
//    }
}
