package com.teserty.spring3.controllers;

import com.teserty.spring3.services.DTOService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsController {
    @Autowired
    public ItemsController(DTOService dtoService) {
        this.dtoService = dtoService;
    }
    private final DTOService dtoService;
}
