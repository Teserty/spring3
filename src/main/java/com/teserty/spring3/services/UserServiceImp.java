package com.teserty.spring3.services;

import com.teserty.spring3.enities.Role;
import com.teserty.spring3.enities.User;
import com.teserty.spring3.repositories.RoleRepository;
import com.teserty.spring3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Configurable
public class UserServiceImp extends UserDetailsServiceImp implements UserService {
    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RolesServiceImp rolesService) {
        super(userRepository, roleRepository, bCryptPasswordEncoder, rolesService);
    }

    public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Optional<User> user = userRepository.findByUsername(((org.springframework.security.core.userdetails.User) principal).getUsername());
            return user.orElse(null);
        }
        return null;

    }

    public boolean isUserWithUsernameExist(String username){
        return findUserByUsername(username).isPresent();
    }

    public void createUser(String username, String password) {
        Set<Role> roles = new HashSet<>();
        roles.add(rolesService.getRoleByName("USER"));
        userRepository.save(User.builder()
                .username(username)
                .password(password)
                .isLocked(false)
                .isEnabled(true)
                .passwordConfirm(bCryptPasswordEncoder.encode(password))
                .roles(roles)
                .build());
    }
}