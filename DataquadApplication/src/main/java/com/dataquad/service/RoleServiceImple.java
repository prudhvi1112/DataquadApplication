package com.dataquad.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dataquad.exceptions.DataNotFoundException;
import com.dataquad.exceptions.RoleAlreadyExistException;
import com.dataquad.exceptions.RoleNotExistException;
import com.dataquad.model.RoleDto;
import com.dataquad.model.RoleModel;
import com.dataquad.repository.RoleDao;

@Service
public class RoleServiceImple implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private ModelMapper modelMapper;

	private static final String ROLE_NOT_FOUND_MESSAGE = "Role Not Found With RoleId : ";
	private static final String ROLE_ALREADY_EXIST_MESSAGE = "Role Already Exist : ";
	private static final String DATA_NOT_FOUND_MESSAGE = "Data Not Found ";

	@Override
	@Transactional
	public RoleDto addRole(RoleDto roleDto) {
		RoleModel roleModel = modelMapper.map(roleDto, RoleModel.class);
		roleModel.setRoleName(roleModel.getRoleName().toUpperCase());
		RoleModel roleName = roleDao.findByRoleName(roleModel.getRoleName());
		if (roleName != null) {
			throw new RoleAlreadyExistException(ROLE_ALREADY_EXIST_MESSAGE + roleDto.getRoleName());
		} else {
			return modelMapper.map(roleDao.save(roleModel), RoleDto.class);
		}

	}

	@Override
	@Transactional
	public RoleDto updateRole(int roleId, RoleDto roleDto) {
		RoleModel roleModel = roleDao.findById(roleId)
				.orElseThrow(() -> new RoleNotExistException(ROLE_NOT_FOUND_MESSAGE + roleId));
		roleModel.setRoleName(roleDto.getRoleName().toUpperCase());
		return modelMapper.map(roleDao.save(roleModel), RoleDto.class);
	}

	@Override
	@Transactional
	public RoleDto getRole(int roleId) {
		RoleModel roleModel = roleDao.findById(roleId)
				.orElseThrow(() -> new RoleNotExistException(ROLE_NOT_FOUND_MESSAGE + roleId));
		return modelMapper.map(roleModel, RoleDto.class);
	}

	@Override
	@Transactional
	public List<RoleDto> getAllRoles() {
		List<RoleModel> allRoles = roleDao.findAll();

		if (allRoles.isEmpty()) {
			throw new DataNotFoundException(DATA_NOT_FOUND_MESSAGE);
		} else {

			return allRoles.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
		}
	}

	@Override
	@Transactional
	public boolean deleteRole(int roleId) {
		RoleModel roleModel = roleDao.findById(roleId)
				.orElseThrow(() -> new RoleNotExistException(ROLE_NOT_FOUND_MESSAGE + roleId));

		roleDao.delete(roleModel);

		return true;
	}

}
