package com.sena.crudbasic.service.impl;

import com.sena.crudbasic.dto.request.LearnerCreateDTO;
import com.sena.crudbasic.model.Learner;
import com.sena.crudbasic.model.UserSystms;
import com.sena.crudbasic.repository.ILearnerRepository;
import com.sena.crudbasic.repository.IUserSystmsRepository;
import com.sena.crudbasic.service.ILearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearnerServiceImpl implements ILearnerService {

    private final ILearnerRepository learnerRepository;
    private final IUserSystmsRepository userSystmsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Learner> findAll() {
        return learnerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Learner findById(Integer id) {
        return learnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Learner no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Learner create(LearnerCreateDTO dto) {
        UserSystms systemUser = userSystmsRepository.findById(dto.getSystemUserId())
                .orElseThrow(() -> new RuntimeException(
                        "Usuario del sistema no encontrado con ID: " + dto.getSystemUserId()));

        Learner learner = Learner.builder()
                .learnerName(dto.getFullName())
                .systemUser(systemUser)
                .build();

        return learnerRepository.save(learner);
    }

    @Override
    @Transactional
    public Learner update(Integer id, LearnerCreateDTO dto) {
        Learner existing = findById(id);

        UserSystms systemUser = userSystmsRepository.findById(dto.getSystemUserId())
                .orElseThrow(() -> new RuntimeException(
                        "Usuario del sistema no encontrado con ID: " + dto.getSystemUserId()));

        existing.setLearnerName(dto.getFullName());
        existing.setSystemUser(systemUser);

        return learnerRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!learnerRepository.existsById(id)) {
            throw new RuntimeException("Learner no encontrado con ID: " + id);
        }
        learnerRepository.deleteById(id);
    }
}
