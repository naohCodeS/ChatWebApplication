package jp.ac.shibaura_it.infolab1.chat.user.exception;

public class UserNameDuplicateException extends Exception{
    public UserNameDuplicateException(String msg){
        super(msg);
    }
}
