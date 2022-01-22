package xyz.chenprime.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "token验证错误")
public class BadTokenException extends RuntimeException{
    public BadTokenException() {
    }

    public BadTokenException(String message) {
        super(message);
    }
}
