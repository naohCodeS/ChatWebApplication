package jp.ac.shibaura_it.infolab1.chat.web;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    private String username;
    @Pattern(regexp = "[0-9a-zA-Z]+")
    @Size(min = 5)
    private String password;
}
