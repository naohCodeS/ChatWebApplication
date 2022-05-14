package jp.ac.shibaura_it.infolab1.chat.web;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.service.LoginUserDetails;
import jp.ac.shibaura_it.infolab1.chat.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("register")
public class RegisterController {
    @Autowired
    UserService userService;

    @ModelAttribute
    RegisterForm setUpForm(){
        return new RegisterForm();
    }

    @GetMapping
    String register(Model model){
        return "register/register";
    }

    @PostMapping(path = "register")
    String register(@Validated RegisterForm form, BindingResult result, Model model,
                    @AuthenticationPrincipal LoginUserDetails userDetails){
        if(result.hasErrors()){
            return register(model);
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        userService.create(user);
        return "redirect:/register";
    }
}