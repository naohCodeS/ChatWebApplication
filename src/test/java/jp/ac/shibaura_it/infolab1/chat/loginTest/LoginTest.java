package jp.ac.shibaura_it.infolab1.chat.loginTest;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import jp.ac.shibaura_it.infolab1.chat.repository.UserRepository;
import jp.ac.shibaura_it.infolab1.chat.service.LoginUserDetails;
import jp.ac.shibaura_it.infolab1.chat.service.LoginUserDetailsService;
import jp.ac.shibaura_it.infolab1.chat.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginTest {
    @Autowired
    UserService userService;

    @Test
    void test() throws InvalidPasswordException {
        userService.create(new User());
        System.out.println(userService.findAll());
    }
}
