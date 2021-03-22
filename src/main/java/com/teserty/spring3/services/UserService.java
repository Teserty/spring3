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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@Configurable
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RolesService rolesService;
    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
    public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Optional<User> user = userRepository.findByUsername(((org.springframework.security.core.userdetails.User) principal).getUsername());
            return user.orElse(null);
        }
        return null;

    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
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