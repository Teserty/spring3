package com.teserty.spring3.handlers;

import com.teserty.spring3.exceptions.ItemNotFoundException;
import com.teserty.spring3.exceptions.ShopNotFoundException;
import com.teserty.spring3.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionHandlerController {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserNotFoundException.class)
    public String userNotFound() {
        LOG.error("User not founded");
        return "User not founded";
    }
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ItemNotFoundException.class)
    public String itemNotFound() {
        LOG.error("Shop not founded");
        return "Shop not founded";
    }
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ShopNotFoundException.class)
    public String shopNotFound() {
        LOG.error("Item not founded");
        return "Item not founded";
    }
}
