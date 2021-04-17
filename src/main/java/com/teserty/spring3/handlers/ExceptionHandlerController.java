package com.teserty.spring3.handlers;

import com.teserty.spring3.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionHandlerController {
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserNotFoundException.class)
    public String userNotFound(){
        return "User not founded";
    }
}
