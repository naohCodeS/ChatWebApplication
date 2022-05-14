package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ログイン時に認証ユーザをuserテーブルから取得するクラス
 */
@Service //DIにUserDetailsServiceは1つの時自動的にそのBeanが使われる
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).get();
        if(user == null){
            throw new UsernameNotFoundException("The requested user is not found");
        }
        return new LoginUserDetails(user);
    }
}
