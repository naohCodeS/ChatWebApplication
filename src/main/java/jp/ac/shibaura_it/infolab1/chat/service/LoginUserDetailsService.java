package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //DIにUserDetailsServiceは1つの時自動的にそのBeanが使われる
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("userDetails was called");
        User userTmp = new User();
        userTmp.setUsername(username);
        User user = userRepository.findOne(Example.of(userTmp)).get();
        if(user == null){
            throw new UsernameNotFoundException("The requested user is not found");
        }
        return new LoginUserDetails(user);
    }
}
