package com.teserty.spring3.services;

import com.teserty.spring3.enities.Role;
import com.teserty.spring3.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImp {
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    private RoleRepository roleRepository;
    public Role getRoleByName(String name){
        return roleRepository.findByName(name);
    }
}
