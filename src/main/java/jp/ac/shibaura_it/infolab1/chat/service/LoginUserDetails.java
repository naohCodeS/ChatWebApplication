package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;


public class LoginUserDetails extends org.springframework.security.core.userdetails.User{
    private final User user;

    public LoginUserDetails(User user){
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public User getUser(){return this.user;}
}
