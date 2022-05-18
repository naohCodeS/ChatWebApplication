package jp.ac.shibaura_it.infolab1.chat.repository;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
