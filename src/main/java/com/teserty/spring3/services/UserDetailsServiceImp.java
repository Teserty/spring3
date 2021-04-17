package com.teserty.spring3.services;

import com.teserty.spring3.entity.Role;
import com.teserty.spring3.entity.User;
import com.teserty.spring3.repositories.RoleRepository;
import com.teserty.spring3.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDetailsServiceImp implements UserDetailsService {
    protected final UserRepository userRepository;
    protected final RoleRepository roleRepository;
    protected final BCryptPasswordEncoder bCryptPasswordEncoder;
    protected final RolesServiceImp rolesService;

    public UserDetailsServiceImp(UserRepository userRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          RolesServiceImp rolesService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.rolesService = rolesService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }
    public void setUserIsLocked(Long id, boolean isLocked){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setLocked(isLocked);
            userRepository.save(user.get());
        }
    }
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if (!userFromDB.isPresent()) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
