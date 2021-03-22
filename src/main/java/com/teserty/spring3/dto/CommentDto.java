package com.teserty.spring3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;

@Getter
@Setter
@Builder
public class CommentDto implements Serializable {
    private String text;
    private String author;
    private Time time;
}
