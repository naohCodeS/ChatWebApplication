package jp.ac.shibaura_it.infolab1.chat.web;

import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.service.LoginUserDetails;
import jp.ac.shibaura_it.infolab1.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    UserService userService;

    @GetMapping(path = "chat")
    String chatForm(@AuthenticationPrincipal LoginUserDetails userDetails, Model model){
        model.addAttribute("channelList", userDetails.getUser().getChannels());
        return "/chatForm";
    }

    @PostMapping(path = "channel")
    String channel(Model model, @AuthenticationPrincipal LoginUserDetails userDetails){
        User user = userDetails.getUser();
        return "redirect:/chatForm";
    }
}
