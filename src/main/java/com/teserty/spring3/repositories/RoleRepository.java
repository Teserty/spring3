package com.teserty.spring3.repositories;

import com.teserty.spring3.enities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}