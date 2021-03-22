package com.teserty.spring3.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserDto implements Serializable {
    private String username;
}
