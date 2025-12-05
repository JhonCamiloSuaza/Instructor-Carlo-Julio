package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.UserSystemCreateDTO;
import com.sena.crudbasic.dto.response.UserSystemResponseDTO;
import com.sena.crudbasic.model.UserSystms;
import com.sena.crudbasic.repository.IUserSystmsRepository;
import com.sena.crudbasic.service.IUserSystmsService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserSystmsServiceImpl
        extends GenericServiceImpl<UserSystms, UserSystemCreateDTO, UserSystemResponseDTO, Integer>
        implements IUserSystmsService {

    private final IUserSystmsRepository userSystmsRepository;

    @Override
    public UserSystemResponseDTO findByLoginName(String loginName) {
        UserSystms user = userSystmsRepository.findByLoginName(loginName)
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado con login: " + loginName));
        return mapToResponse(user);
    }

    @Override
    public boolean existsByLoginName(String loginName) {
        return userSystmsRepository.existsByLoginName(loginName);
    }

    @Override
    public List<UserSystemResponseDTO> findAllUsers() {
        return userSystmsRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    protected UserSystms mapToEntity(UserSystemCreateDTO dto) {
        return UserSystms.builder()
                .loginName(dto.getLoginName())
                .build();
    }

    @Override
    protected void updateEntity(UserSystms entity, UserSystemCreateDTO dto) {
        entity.setLoginName(dto.getLoginName());
    }

    @Override
    protected UserSystemResponseDTO mapToResponse(UserSystms user) {
        return UserSystemResponseDTO.builder()
                .id(user.getId())
                .loginName(user.getLoginName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    protected JpaRepository<UserSystms, Integer> getRepository() {
        return userSystmsRepository;
    }

    @Override
    protected String getEntityName() {
        return "Usuario del Sistema";
    }
}
