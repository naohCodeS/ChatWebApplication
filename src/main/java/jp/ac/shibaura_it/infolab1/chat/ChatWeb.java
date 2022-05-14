//package jp.ac.shibaura_it.infolab1.chat;
//
//import jp.ac.shibaura_it.infolab1.chat.domain.Channel;
//import jp.ac.shibaura_it.infolab1.chat.repository.ChannelList;
//import jp.ac.shibaura_it.infolab1.chat.exception.channel.ChannelNameDuplicateException;
//import jp.ac.shibaura_it.infolab1.chat.exception.channel.notExistChannelException;
//import jp.ac.shibaura_it.infolab1.chat.exception.chat.ChatNullException;
//import jp.ac.shibaura_it.infolab1.chat.domain.User;
//import jp.ac.shibaura_it.infolab1.chat.repository.UserList;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class ChatWeb {
//    String username = "";
//    String password = "";
//    private static String errorMessage = "";
//    static UserList userList;
//    static ChannelList channelList;
//
//    private User user;
//
//    public ChatWeb(){
//        errorMessage = "";
//        userList = new UserList();
//        channelList = new ChannelList();
//    }
//
//    @RequestMapping(value = "/login")
//    String login(Model model){
//        model.addAttribute("username", username);
//        model.addAttribute("password", password);
//        model.addAttribute("errorMessage", errorMessage);
//
//        System.out.println("displayed login scene");
//
//        return "login";
//    }
//
//    @RequestMapping(value = "loginForm")
//    String login(@RequestParam("username") String username,
//                 @RequestParam("password") String password){
//        this.username = username;
//        this.password = password;
//        try {
//            user = userList.login(this.username, this.password);
//            errorMessage = "";
//            return "redirect:/home";
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            errorMessage = e.getMessage();
//            return "redirect:/login";
//        }
//    }
//
//    @RequestMapping(value = "/register")
//    String register(Model model){
//        model.addAttribute("username", username);
//        model.addAttribute("password", password);
//        model.addAttribute("errorMessage", errorMessage);
//
//        System.out.println("displayed register scene");
//
//        return "register";
//    }
//
//    @RequestMapping(value = "/registerForm")
//    String register(@RequestParam("username") String username,
//                    @RequestParam("password") String password){
//        this.username = username;
//        this.password = password;
//        try {
//            userList.addUser(this.username, this.password);
//            this.user = userList.login(this.username, this.password);
//            errorMessage = "";
//            return "redirect:/home";
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            errorMessage = e.getMessage();
//            return "redirect:/register";
//        }
//    }
//
//    @RequestMapping(value = "/home")
//    String home(Model model){
//        model.addAttribute("username", this.user.getUsername());
//        model.addAttribute("channelList", channelList);
////        model.addAttribute("currentChannelName", this.user.getCurrentChannelName());
////        model.addAttribute("chatList", this.user.getCurrentChannel().getChatList());
//
//        System.out.println("displayed home scene");
//
//        return "home";
//    }
//
//    @RequestMapping(value = "addChannel")
//    String addChannel(@RequestParam("channelName") String channelName){
////        Channel channel = new Channel(channelName);
////        try {
//////            channelList.addChannel(channel);
////        } catch (ChannelNameDuplicateException e) {
////            System.out.println(e.getMessage());
////        }
//        return "redirect:/home";
//    }
//
//    @RequestMapping(value="/selectChannel")
//    String selectChannel(@RequestParam("channelName") String channelName){
//        channelName = channelName.substring(1);
////        try {
////            this.user.channelSelect(channelList.getChannelFromChannelName(channelName));
////        } catch (notExistChannelException e) {
////            System.out.println(e.getMessage());
////        }
//        return "redirect:/home";
//    }
//
//    @RequestMapping(value = "/add")
//    String addChat(@RequestParam("chat") String chat) {
////        try {
////            this.user.chat(chat);
////        } catch (ChatNullException e) {
////            System.out.println(e.getMessage());
////        }
//        return "redirect:/home";
//    }
//}