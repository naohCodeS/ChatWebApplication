package jp.ac.shibaura_it.infolab1.chat.exception.user;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String msg){
        super(msg);
    }
}
