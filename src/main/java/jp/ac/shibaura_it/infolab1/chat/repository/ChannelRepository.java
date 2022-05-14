package jp.ac.shibaura_it.infolab1.chat.repository;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {
}
