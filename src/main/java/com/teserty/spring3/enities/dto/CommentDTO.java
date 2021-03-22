package com.teserty.spring3.enities.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CommentDTO implements Serializable {
    private String text;
    private UserDTO author;
}
