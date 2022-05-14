package jp.ac.shibaura_it.infolab1.chat.exception.user;

public class PasswordUnmatchException extends Exception{
    public PasswordUnmatchException(String msg){
        super(msg);
    }
}
