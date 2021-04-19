package com.teserty.spring3.controllers;

import com.teserty.spring3.dto.Response;
import com.teserty.spring3.entity.Role;
import com.teserty.spring3.entity.User;
import com.teserty.spring3.exceptions.UserNotFoundException;
import com.teserty.spring3.services.RolesServiceImp;
import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp userService;
    private final RolesServiceImp rolesServiceImp;
    @Autowired
    public UserController(UserServiceImp userService, RolesServiceImp rolesServiceImp) {
        this.userService = userService;
        this.rolesServiceImp = rolesServiceImp;
    }

    @PostMapping("/registration")
    public Response addUser(@RequestParam String username, @RequestParam String password) {
        if (!userService.isUserWithUsernameExist(username)){
            userService.createUser(username, password);
            return new Response("200", "Пользователь создан");
        }else{
            return new Response("400", "Пользователь c этим именем уже существует");
        }
    }
    //@PostMapping("/grand")
    //public Response grandMe(Authentication authentication, @RequestBody String grandCode){
    //    if(grandCode.equals("666")){
    //        Optional<User> opuser = userService.getUserByUsername(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername());
    //        if (opuser.isPresent()) {
    //            User user = opuser.get();
    //            Set<Role> roles = user.getRoles();
    //            roles.add(rolesServiceImp.getRoleByName("ADMIN"));
    //            user.setRoles(roles);
    //            userService.saveUser(user);
    //            return new Response("200", "OK");
    //        }else{
    //            throw new UserNotFoundException();
    //        }
    //    }else{
    //        return new Response("500", "Wrong Code");
    //    }
    //}
}