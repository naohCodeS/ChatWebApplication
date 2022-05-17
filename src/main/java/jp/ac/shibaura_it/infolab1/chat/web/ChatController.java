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
        System.out.println(":: get map ::");

        model.addAttribute("channelNullError", channelNullError);
        model.addAttribute("channelList", channelService.findAll());
        model.addAttribute("username", userDetails.getUsername());

        if(userDetails.getUser().getCurrentChannel() != null){
            Channel currentChannel = userDetails.getUser().getCurrentChannel();
            model.addAttribute("userList", currentChannel.getUsers());
            model.addAttribute("currentChannelName", userDetails.getUser().getCurrentChannel().getChannelName());
            model.addAttribute("chatList", userDetails.getUser().getCurrentChannel().getChats());
            userDetails.getUser().setCurrentChannel(channelService.findOne(userDetails.getUser().getCurrentChannel().getId()));
        }
        return "/chatForm";
    }

    @PostMapping(path = "channel")
    String channel(Model model, @AuthenticationPrincipal LoginUserDetails userDetails){
        System.out.println(":: post channel ::");
        return "redirect:/chatForm";
    }

    @RequestMapping(value = "createChannel")
    String createChannel(@RequestParam("channelName")String channelName,
                         @AuthenticationPrincipal LoginUserDetails userDetails){
        System.out.println(":: create channel ::");
        Channel channel = new Channel(); channel.setChannelName(channelName);
        channelService.create(channel, userDetails.getUser());
        System.out.println("created channel : " + channel);
        return "redirect:/chat";
    }

    @RequestMapping(value = "/selectChannel")
    String selectChannel(@RequestParam("channelName")String channelName,
                         @AuthenticationPrincipal LoginUserDetails userDetails){
        System.out.println(":: select channel ::");

        Integer id = Integer.valueOf(channelName.split(" / ")[channelName.split(" / ").length - 1]);
        Channel selectedChannel = channelService.findOne(id);
        User user = userDetails.getUser();

//        if(user.getCurrentChannel() != null) channelService.deleteUser(user.getCurrentChannel(), user);
        if(selectedChannel == user.getCurrentChannel()) return "redirect:/chat";

        userService.addChannel(user, selectedChannel);
        userService.changeCurrentChannel(user, selectedChannel);
        userService.update(user);

        channelService.addUser(selectedChannel, user);

        if(channelNullError != null) channelNullError = null;

        System.out.println("selected channel : "+selectedChannel);
        return "redirect:/chat";
    }

    @RequestMapping(value = "/add")
    String addChat(@AuthenticationPrincipal LoginUserDetails userDetails,
                   @RequestParam(value = "chat")String chatText){
        System.out.println(":: add chat ::");

        User user = userDetails.getUser();
        Channel channel = user.getCurrentChannel();
        Chat chat = new Chat(); chat.setChatText(chatText);

        try {
            chatService.create(chat, channel, user);
            channelService.addChat(channel, chat);
        } catch (ChannelNullException e) {
            channelNullError = e.getMessage();
        }

        System.out.println(userDetails.getUser() + " created " + chat + " to " + channel);

        return "redirect:/chat";
    }
}
