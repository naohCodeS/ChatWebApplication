package jp.ac.shibaura_it.infolab1.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jp.ac.shibaura_it.infolab1.chat.exception.channel.notExistChannelException;
import jp.ac.shibaura_it.infolab1.chat.exception.chat.ChatNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private String username;
//    @Column(nullable = false)
    @JsonIgnore
    private String password;
//    @Column
//    private Channel currentChannel;
    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, //永続化操作や削除操作をChannelにも伝播させる
                fetch = FetchType.EAGER, //channelへのアクセスはフィールドアクセスまで遅延させる
                mappedBy = "users") //関連先でのプロパティ名
    private List<Channel> channels;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user")
    private List<Chat> chats;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "id")
    private Channel currentChannel;

    public User(String userName, String password) throws InvalidPasswordException {
        this.setAccountInfo(userName, password);
//        this.currentChannel = new Channel("Private Channel");
    }

    public void setAccountInfo(String userName, String password) throws InvalidPasswordException {
        this.setUsername(userName);
        this.setPassword(password);
    }
    public void setPassword(String password) throws InvalidPasswordException {
        if(password.matches("[0-9a-zA-Z]+"))
            this.password = password;
        else throw new InvalidPasswordException("Invalid password");
    }
//    public void channelSelect(Channel channel) throws notExistChannelException {
//        System.out.println("channel was changed to "+channel.getChannelName());
//        this.currentChannel = channel;
//    }
//    public void chat(String message) throws ChatNullException {
//        Chat chat = new Chat(this, message);
////        this.currentChannel.addChat(chat);
//    }
    @Override
    public boolean equals(Object obj){
        // なんで (User)obj.getPassword()が動くのかわからない。privateにしているのに...
        return this.username.equals(((User)obj).getUsername());
//        return this.userName.equals(((User)obj).getUserName());
    }
//    @Override
//    public String toString(){
//        return "username : "+this.userName+"\n";
//    }
}
