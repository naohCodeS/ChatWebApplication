package jp.ac.shibaura_it.infolab1.chat.web;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.web.ChannelNullException;
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

    static String channelNullError;

    @GetMapping(path = "chat")
    String chatForm(@AuthenticationPrincipal LoginUserDetails userDetails, Model model){

        userDetails.getUser().setChannels(channelService.findAll());

        model.addAttribute("channelNullError", channelNullError);
        model.addAttribute("channelList", userDetails.getUser().getChannels());
        if(userDetails.getUser().getCurrentChannel() != null){
            model.addAttribute("currentChannelName", userDetails.getUser().getCurrentChannel().getChannelName());
            model.addAttribute("chatList", userDetails.getUser().getCurrentChannel().getChats());
            userDetails.getUser().setCurrentChannel(channelService.findOne(userDetails.getUser().getCurrentChannel().getId()));
        }
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
        if(channelNullError != null) channelNullError = null;
        return "redirect:/chat";
    }

    @RequestMapping(value = "/add")
    String addChat(@AuthenticationPrincipal LoginUserDetails userDetails,
                   @RequestParam(value = "chat")String chatText){

        User user = userDetails.getUser();
        Channel channel = user.getCurrentChannel();
        Chat chat = new Chat();
        chat.setChatText(chatText);

        try {
            chatService.create(chat, channel, user);
        } catch (ChannelNullException e) {
            channelNullError = e.getMessage();
        }

        System.out.println("chat");
        System.out.println(userDetails.getUser() +"\n"+chatText);

        return "redirect:/chat";
    }
}
