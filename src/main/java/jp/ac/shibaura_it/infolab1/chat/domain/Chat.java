package jp.ac.shibaura_it.infolab1.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jp.ac.shibaura_it.infolab1.chat.exception.chat.ChatNullException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "chat")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer chat_id;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "username")
    private User user;
    private String chatText;
    private Calendar calendar;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "id")
    private Channel channel;

//    public Chat(User user, String chat) throws ChatNullException {
//        calendar = Calendar.getInstance();
//        this.setUser(user);
//        this.setChat(chat);
//    }
//    private void setChat(String chat) throws ChatNullException {
//        if(chat.isEmpty()) throw new ChatNullException("Chat is Null");
//        this.chat = chat;
//    }
//    public String getChattedTime(){
//        String month = String.valueOf(this.calendar.get(Calendar.MONTH) + 1);
//        String day = String.valueOf(this.calendar.get(Calendar.DAY_OF_MONTH));
//        String hour = String.valueOf(this.calendar.get(Calendar.HOUR));
//        String minute = String.valueOf(this.calendar.get(Calendar.MINUTE));
//        return month+"/"+day+"/"+hour+":"+minute;
//    }
//    @Override
//    public String toString(){
//
//    }
}
