package com.dataquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataquad.model.RoleDto;

import com.dataquad.service.RoleServiceImple;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleServiceImple roleService;

	@PostMapping("/")
	public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
		return new ResponseEntity<>(roleService.addRole(roleDto), HttpStatus.CREATED);

	}

	@PutMapping("/{roleId}")
	public ResponseEntity<RoleDto> updateRoleData(@PathVariable("roleId") int roleId, @RequestBody RoleDto roleDto) {
		return new ResponseEntity<>(roleService.updateRole(roleId, roleDto), HttpStatus.OK);

	}

	@GetMapping("/{roleId}")
	public ResponseEntity<RoleDto> getRoleData(@PathVariable("roleId") int roleId) {
		return new ResponseEntity<>(roleService.getRole(roleId), HttpStatus.FOUND);

	}

	@GetMapping("/")
	public ResponseEntity<List<RoleDto>> getRolesList() {
		return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.FOUND);
	}

	@DeleteMapping("/{roleId}")
	public ResponseEntity<Void> deleteRole(@PathVariable("roleId") int roleId) {
		roleService.deleteRole(roleId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
