package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;



public class LoginUserDetails extends org.springframework.security.core.userdetails.User{
    private final User user;

    public LoginUserDetails(User user){
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public User getUser(){return this.user;}
}
