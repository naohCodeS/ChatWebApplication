package jp.ac.shibaura_it.infolab1.chat.domainTest;

import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.chat.ChatNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.user.InvalidPasswordException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChatTest {
    @Test
    void constructorTest() throws ChatNullException {
        Chat twoArgs = new Chat(new User(), "Hello");

        assertThat(twoArgs.getUser().getUsername()).isEqualTo(null);
        assertThat(twoArgs.getChat()).isEqualTo("Hello");
    }
}
