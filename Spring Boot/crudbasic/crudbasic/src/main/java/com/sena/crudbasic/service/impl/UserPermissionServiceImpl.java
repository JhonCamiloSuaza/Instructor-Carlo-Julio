package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.User_Permission;
import com.sena.crudbasic.dto.UserPermissionDto;
import com.sena.crudbasic.repository.IUserPermissionRepository;
import com.sena.crudbasic.repository.IUserSystmsRepository;
import com.sena.crudbasic.repository.IPermissionRepository;
import com.sena.crudbasic.service.IUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPermissionServiceImpl implements IUserPermissionService {

    @Autowired
    private IUserPermissionRepository repository;

    @Autowired
    private IUserSystmsRepository userRepository;

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List<UserPermissionDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserPermissionDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public UserPermissionDto save(UserPermissionDto dto) {
        User_Permission up = new User_Permission();
        up.setSystemUser(userRepository.findById(dto.getSystemUserId()).orElse(null));
        up.setPermission(permissionRepository.findById(dto.getPermissionId()).orElse(null));
        return convertToDto(repository.save(up));
    }

    @Override
    public UserPermissionDto update(int id, UserPermissionDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setSystemUser(userRepository.findById(dto.getSystemUserId()).orElse(null));
            existing.setPermission(permissionRepository.findById(dto.getPermissionId()).orElse(null));
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private UserPermissionDto convertToDto(User_Permission up) {
        UserPermissionDto dto = new UserPermissionDto();
        dto.setId(up.getId());
        dto.setSystemUserId(up.getSystemUser() != null ? up.getSystemUser().getId() : 0);
        dto.setPermissionId(up.getPermission() != null ? up.getPermission().getId() : 0);
        return dto;
    }
}
