package jp.ac.shibaura_it.infolab1.chat.web;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.Chat;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.channel.ChannelNameNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.channel.ChannelNullException;
import jp.ac.shibaura_it.infolab1.chat.exception.chat.ChatTextNullException;
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

import java.time.format.DateTimeFormatter;

/*
Channel currentChannelではなく
Integer currentChannelIdでいけるのでは？
 */

@Controller
public class ChatController {
    @Autowired
    UserService userService;
    @Autowired
    ChannelService channelService;
    @Autowired
    ChatService chatService;

    static String channelNullError;
    static String chatTextNullError;
    static String channelNameNullError;

    @GetMapping(path = "chat")
    String chatForm(@AuthenticationPrincipal LoginUserDetails userDetails, Model model){
        System.out.println(":: get map ::");
        model.addAttribute("channelNullError", channelNullError);
        model.addAttribute("channelList", channelService.findAll());
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("DateFormat", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if(userDetails.getUser().getCurrentChannel() != null){
            System.out.println(userDetails.getUser().getCurrentChannel().getChats());
            Channel currentChannel = userDetails.getUser().getCurrentChannel();
            model.addAttribute("userList", channelService.findOne(currentChannel.getId()).getUsers());
            model.addAttribute("currentChannelName", userDetails.getUser().getCurrentChannel().getChannelName());
            model.addAttribute("chatList", channelService.findOne(userDetails.getUser().getCurrentChannel().getId()).getChats());
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
        try {
            channelService.create(channel, userDetails.getUser());
        } catch (ChannelNameNullException e) {
            channelNameNullError = e.getMessage();
        }
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

        if(user.getCurrentChannel()!=null && selectedChannel.equals(user.getCurrentChannel())) return "redirect:/chat";

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

        if(chatTextNullError != null) chatTextNullError = null;

        User user = userDetails.getUser();
        Channel channel = user.getCurrentChannel();
        Chat chat = new Chat(); chat.setChatText(chatText);

        try {
            chatService.create(chat, channel, user);
            channelService.addChat(channel, chat);

            System.out.println(chat.getTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        } catch (ChannelNullException e) {
            channelNullError = e.getMessage();
        } catch (ChatTextNullException e) {
            chatTextNullError = e.getMessage();
        }

        System.out.println(userDetails.getUser() + " created " + chat + " to " + channel);

        return "redirect:/chat";
    }
}
