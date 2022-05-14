package jp.ac.shibaura_it.infolab1.chat.web;

import jp.ac.shibaura_it.infolab1.chat.domain.User;
import jp.ac.shibaura_it.infolab1.chat.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @ModelAttribute
    RegisterForm setUpForm(){
        System.out.println(1111111);
        return new RegisterForm();
    }

    @GetMapping(path = "registerForm")
    String register(Model model){
        System.out.println("registerForm");
        System.out.println(model);
        System.out.println(userService.findAll());
        return "/registerForm";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    String register(@Validated RegisterForm form, BindingResult result, Model model){
        if(result.hasErrors()){
            return register(model);
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        userService.create(user);

        return "redirect:/registerForm";
    }

}
