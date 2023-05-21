package ru.skypro.lessons.springboot.weblibrary.exceptions;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message) {
        super(message);
    }
}
