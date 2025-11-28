package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.UserSystmsDto;
import java.util.List;

public interface IUserSystmsService {
    List<UserSystmsDto> getAll();
    UserSystmsDto getById(int id);
    UserSystmsDto save(UserSystmsDto dto);
    UserSystmsDto update(int id, UserSystmsDto dto);
    void delete(int id);
}
