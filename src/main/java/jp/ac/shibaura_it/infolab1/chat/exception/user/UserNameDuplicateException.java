package jp.ac.shibaura_it.infolab1.chat.exception.user;

public class UserNameDuplicateException extends Exception{
    public UserNameDuplicateException(String msg){
        super(msg);
    }
}
