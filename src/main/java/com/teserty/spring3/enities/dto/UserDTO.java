package com.teserty.spring3.enities.dto;


import com.teserty.spring3.enities.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserDTO implements Serializable {
    private String username;
    private Set<Role> roles;
}
