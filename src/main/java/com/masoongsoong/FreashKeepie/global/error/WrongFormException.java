package com.masoongsoong.FreashKeepie.global.error;

public class WrongFormException extends Exception {
    static final long serialVersionUID = 1L;

    public WrongFormException() {
        super("입력 값 중 null 값이 있습니다");
    }

    public WrongFormException(String message) {
        super(message);
    }

}