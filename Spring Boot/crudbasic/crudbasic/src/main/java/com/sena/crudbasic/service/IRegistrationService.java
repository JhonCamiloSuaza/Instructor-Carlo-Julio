package com.sena.crudbasic.service;

import com.sena.crudbasic.dto.RegistrationDto;
import java.util.List;

public interface IRegistrationService {
    List<RegistrationDto> getAll();
    RegistrationDto getById(int id);
    RegistrationDto save(RegistrationDto dto);
    RegistrationDto update(int id, RegistrationDto dto);
    void delete(int id);
}
