package jp.ac.shibaura_it.infolab1.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, //永続化操作や削除操作をChannelにも伝播させる
                fetch = FetchType.EAGER, //channelへのアクセスはフィールドアクセスまで遅延させる
                mappedBy = "users") //関連先でのプロパティ名
    private List<Channel> channels;

    //これいらないかも
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user")
    private List<Chat> chats;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Channel currentChannel;

    public User(String userName, String password) throws InvalidPasswordException {
        this.setAccountInfo(userName, password);
    }

    public void setAccountInfo(String userName, String password) throws InvalidPasswordException {
        this.setUsername(userName);
        this.setPassword(password);
    }
    public void setPassword(String password) throws InvalidPasswordException {
        if(password.matches("[/da-zA-Z]+"))
            this.password = password;
        else throw new InvalidPasswordException("Invalid password");
    }
    @Override
    public boolean equals(Object obj){
        return this.username.equals(((User)obj).getUsername());
    }
}
