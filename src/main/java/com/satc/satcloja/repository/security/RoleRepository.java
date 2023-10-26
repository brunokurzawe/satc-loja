package com.satc.satcloja.repository.security;

import com.satc.satcloja.model.security.ERole;
import com.satc.satcloja.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
