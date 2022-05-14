package jp.ac.shibaura_it.infolab1.chat.domainTest;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ChannelTest {
    @Test
    void constructorTest(){
        Channel noArgsChannel = new Channel();
        Channel oneArgsChannel = new Channel("channelName");
//        Channel allArgsChannel = new Channel(1, "channelName");

        assertThat(noArgsChannel.getId()).isEqualTo(null);
        assertThat(noArgsChannel.getChannelName()).isEqualTo(null);

        assertThat(oneArgsChannel.getId()).isEqualTo(null);
        assertThat(oneArgsChannel.getChannelName()).isEqualTo("channelName");

//        assertThat(allArgsChannel.getId()).isEqualTo(1);
//        assertThat(allArgsChannel.getChannelName()).isEqualTo("channelName");

        System.out.println(noArgsChannel);
        System.out.println(oneArgsChannel);
//        System.out.println(allArgsChannel);
    }
}
