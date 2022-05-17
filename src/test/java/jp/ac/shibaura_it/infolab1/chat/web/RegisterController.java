package jp.ac.shibaura_it.infolab1.chat.web;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.exception.user.UserNameDuplicateException;
import jp.ac.shibaura_it.infolab1.chat.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "registerForm")
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    ChannelService channelService;
    @Autowired
    ChatService chatService;

    static String errorMessage = null;

    @ModelAttribute
    RegisterForm setUpForm(){
        return new RegisterForm();
    }

    @GetMapping
    String register(Model model){
        model.addAttribute("errorMessage", errorMessage);
        System.out.println(userService.findAll());
        return "/registerForm";
    }

    @RequestMapping
    String register(@Validated RegisterForm form, BindingResult result, Model model){
        if(result.hasErrors()){
            return register(model);
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        try {
            user = userService.register(user.getUsername(), user.getPassword());
            return "redirect:/login";
        } catch (UserNameDuplicateException e) {
            System.out.println("error");
            errorMessage = e.getMessage();
            return "redirect:/registerForm";
        }
    }

}
