package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.user.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User create(User user){return userRepository.save(user);}
    public List<User> findAll(){ //read
        return userRepository.findAll();
    }
    public User findOne(String username){return userRepository.findById(username).get();}
    public User update(User user){
        return userRepository.save(user);
    }
    public void delete(String username){userRepository.deleteById(username);}
    public User register(String username, String password) throws UserNameDuplicateException {
        if(!userRepository.findById(username).isEmpty()) throw new UserNameDuplicateException("the username is already used");
        password = new Pbkdf2PasswordEncoder().encode(password);
        User user = new User(username, password, null, null, null);
        return userRepository.save(user);
    }

    public User addChannel(User user, Channel channel){
        System.out.println(":: UserService ::");
        System.out.println(channel.getChats());
        if(!user.getChannels().contains(channel))user.getChannels().add(channel);
        System.out.println(channel.getChats());
        System.out.println("user : "+user.getCurrentChannel());
        return this.update(user);
    }

    public User changeCurrentChannel(User user, Channel channel){
        user.setCurrentChannel(channel);
        return this.update(user);
    }
}
