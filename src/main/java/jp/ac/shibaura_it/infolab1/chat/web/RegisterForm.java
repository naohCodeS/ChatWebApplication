package jp.ac.shibaura_it.infolab1.chat.web;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RegisterForm {
    private String username;
    @Pattern(regexp = "[0-9a-zA-Z]+")
    private String password;
}
