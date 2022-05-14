package jp.ac.shibaura_it.infolab1.chat.service;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    }
    public List<User> findAll(){ //read
        return userRepository.findAll();
    }
    public User findOne(String username){
        User user = new User();
        user.setUsername(username);
        return userRepository.findOne(Example.of(user)).get();
    }

    public User update(User user){
        return userRepository.save(user);
    }
    public void delete(String username){
        userRepository.deleteAllById(Collections.singleton(username));
    }

//    public void registerUser(String userName, String password) throws InvalidPasswordException, UserNameDuplicateException {
//        userRepository.;
//    }

//    public User login(String userName, String password) throws PasswordUnmatchException, UserNameUnregisteredException, InvalidPasswordException {
//        return userList.login(userName, password);
//    }
}
