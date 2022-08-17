package com.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>{
	Optional<Roles> findByRoleName(String name);

}
