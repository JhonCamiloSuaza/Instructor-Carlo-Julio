package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.model.UserSystms;
import com.sena.crudbasic.dto.UserSystmsDto;
import com.sena.crudbasic.repository.IUserSystmsRepository;
import com.sena.crudbasic.service.IUserSystmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSystmsServiceImpl implements IUserSystmsService {

    @Autowired
    private IUserSystmsRepository repository;

    @Override
    public List<UserSystmsDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserSystmsDto getById(int id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public UserSystmsDto save(UserSystmsDto dto) {
        UserSystms user = new UserSystms();
        user.setLoginName(dto.getLoginName());
        return convertToDto(repository.save(user));
    }

    @Override
    public UserSystmsDto update(int id, UserSystmsDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setLoginName(dto.getLoginName());
            return convertToDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private UserSystmsDto convertToDto(UserSystms user) {
        UserSystmsDto dto = new UserSystmsDto();
        dto.setId(user.getId());
        dto.setLoginName(user.getLoginName());
        return dto;
    }
}
