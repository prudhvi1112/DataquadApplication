package com.dataquad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataquad.model.RoleModel;

@Repository
public interface RoleDao extends JpaRepository<RoleModel, Integer> {
	RoleModel findByRoleName(String roleName);

}
