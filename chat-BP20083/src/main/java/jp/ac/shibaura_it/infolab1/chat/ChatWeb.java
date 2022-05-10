package jp.ac.shibaura_it.infolab1.chat;
import jp.ac.shibaura_it.infolab1.chat.channel.Channel;
import jp.ac.shibaura_it.infolab1.chat.channel.ChannelList;
import jp.ac.shibaura_it.infolab1.chat.chat.exception.ChatNullException;
import jp.ac.shibaura_it.infolab1.chat.user.User;
import jp.ac.shibaura_it.infolab1.chat.user.UserList;
import jp.ac.shibaura_it.infolab1.chat.user.exception.notExistChannelException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;

/*
未完成
 */
@Controller
public class ChatWeb extends HttpServlet {
    String username = "";
    String password = "";
    private static String errorMessage = "";
    static UserList userList;
    static ChannelList channelList;
    private User user;

    public ChatWeb(){
        errorMessage = "";
        userList = new UserList();
        channelList = new ChannelList();
    }

    @RequestMapping(value = "/login")
    String login(Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @RequestMapping(value = "loginForm")
    String login(@RequestParam("username") String username,
                 @RequestParam("password") String password){
        this.username = username;
        this.password = password;
        try {
            user = userList.login(this.username, this.password);
            errorMessage = "";
            return "redirect:/home";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorMessage = e.getMessage();
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/register")
    String register(Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("errorMessage", errorMessage);
        return "register";
    }

    @RequestMapping(value = "/registerForm")
    String register(@RequestParam("username") String username,
                    @RequestParam("password") String password){
        this.username = username;
        this.password = password;
        try {
            userList.addUser(this.username, this.password);
            this.user = userList.login(this.username, this.password);
            errorMessage = "";
            return "redirect:/home";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorMessage = e.getMessage();
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "/home")
    String home(Model model){
        model.addAttribute("username", this.user.getUserName());
        model.addAttribute("channelList", this.user.getChannelList());
        model.addAttribute("currentChannelName", this.user.getCurrentChannelName());
        model.addAttribute("chatList", this.user.getCurrentChannel().getChatList());
        return "home";
    }

    @RequestMapping(value = "addChannel")
    String addChannel(@RequestParam("channelName") String channelName){
        Channel channel = new Channel(channelName);
        channelList.addChannel(channel);
        try {
            this.user.joinChannel(channelList.getChannelFromChannelName(channelName));
        } catch (notExistChannelException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/add")
    String addChat(@RequestParam("chat") String chat) {
        try {
            this.user.chat(chat);
        } catch (ChatNullException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/home";
    }
}