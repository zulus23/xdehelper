package ru.zhukov.xde.exception;

/**
 * Created by Gukov on 02.06.2017.
 */
public class BaseException extends Exception {
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
