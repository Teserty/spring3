package com.teserty.spring3.enities.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class FeedbackDTO implements Serializable {
    Integer rating;
    private UserDTO author;
}
