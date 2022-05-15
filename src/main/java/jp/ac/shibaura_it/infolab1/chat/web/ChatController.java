package jp.ac.shibaura_it.infolab1.chat.web;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.service.ChannelService;
import jp.ac.shibaura_it.infolab1.chat.service.ChatService;
import jp.ac.shibaura_it.infolab1.chat.service.LoginUserDetails;
import jp.ac.shibaura_it.infolab1.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    UserService userService;
    @Autowired
    ChannelService channelService;
    @Autowired
    ChatService chatService;

    @GetMapping(path = "chat")
    String chatForm(@AuthenticationPrincipal LoginUserDetails userDetails, Model model){
        model.addAttribute("channelList", userDetails.getUser().getChannels());
        model.addAttribute("currentChannelName", userDetails.getUser().getCurrentChannel().getChannelName());
//        model.addAttribute("channelId", userDetails.getUser().getCurrentChannel());
        return "/chatForm";
    }

    @PostMapping(path = "channel")
    String channel(Model model, @AuthenticationPrincipal LoginUserDetails userDetails){
        User user = userDetails.getUser();
        return "redirect:/chatForm";
    }

    @RequestMapping(value = "createChannel")
    String createChannel(@RequestParam("channelName")String channelName,
                         @AuthenticationPrincipal LoginUserDetails userDetails){
        System.out.println("create channel");
        Channel channel = new Channel();
        channel.setChannelName(channelName);
        channelService.create(channel, userDetails.getUser());
        System.out.println(channel.getId());
        return "redirect:/chat";
    }

    @RequestMapping(value = "/selectChannel")
    String selectChannel(@RequestParam("channelName")String channelName,
                         @AuthenticationPrincipal LoginUserDetails userDetails){
        System.out.println(channelName);
        Integer id = Integer.valueOf(channelName.split(" / ")[channelName.split(" / ").length - 1]);
        System.out.println("select channel : " + id);
        userDetails.getUser().setCurrentChannel(channelService.findOne(Integer.valueOf(id)));
        return "redirect:/chat";
    }

    @PostMapping(value = "/add")
    String addChat(@AuthenticationPrincipal LoginUserDetails userDetails,
                   @RequestParam(value = "chat")String chatText){
        User user = userDetails.getUser();
//        Channel channel = user.getCurrentChannel();
        Chat chat = new Chat(null, null, chatText, null, null);

//        chatService.create(chat, channel, user);

        return "redirect:/chat";
    }
}
