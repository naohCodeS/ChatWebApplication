package jp.ac.shibaura_it.infolab1.chat.repository;

import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
