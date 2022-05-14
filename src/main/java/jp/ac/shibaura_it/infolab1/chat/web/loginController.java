package jp.ac.shibaura_it.infolab1.chat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @GetMapping(path = "loginForm")
    String login(){
        return "loginForm";
    }
}
