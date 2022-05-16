package jp.ac.shibaura_it.infolab1.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity //データの入れ物となるクラス
@Table(name = "channel")
@NoArgsConstructor //JPAの仕様でエンティティクラスにはデフォルトコンストラクタが必要
@Data //インスタンス変数のgetter/setter/toString/equals/hashCodeがアクセス可能になる
@AllArgsConstructor //全フィールドを引数に持つコンストラクタを生成
public class Channel {
    @Id //主キー
    @GeneratedValue(strategy = GenerationType.AUTO) //Dialectによって主キーを自動採番
    private Integer id;
    private String channelName;
    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "username")
    private List<User> users;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "channel")
    private List<Chat> chats;

    public Channel(String channelName){
        this.channelName = channelName;
    }

    @Override
    public boolean equals(Object obj){
        return this.id.equals(((Channel)obj).getId());
    }
}
