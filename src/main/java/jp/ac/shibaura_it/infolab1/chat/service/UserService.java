package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    private Channel currentChannel;

    public User create(User user){
        return userRepository.save(user);
    }
    public List<User> findAll(){ //read
        return userRepository.findAll();
    }
    public User findOne(String username){
        return userRepository.findById(username).get();
    }
    public User update(User user){
        return userRepository.save(user);
    }
    public void delete(String username){
        userRepository.deleteById(username);
    }
    public User register(String username, String password) throws UserNameDuplicateException {
        if(!userRepository.findById(username).isEmpty()) throw new UserNameDuplicateException("the username is already used");
        password = new Pbkdf2PasswordEncoder().encode(password);
        User user = new User(username, password, null, null, null);

        return userRepository.save(user);
    }
}
