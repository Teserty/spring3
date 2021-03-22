package com.teserty.spring3.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable{
    private String code;
    private String text;

    public Response(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
