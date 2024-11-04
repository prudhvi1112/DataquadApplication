package com.dataquad.service;

import java.util.List;

import com.dataquad.model.RoleDto;

public interface RoleService {
	public RoleDto addRole(RoleDto roleDto);

	public RoleDto updateRole(int roleId, RoleDto roleDto);

	public RoleDto getRole(int roleId);

	public boolean deleteRole(int roleId);

	public List<RoleDto> getAllRoles();

}
