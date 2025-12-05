package com.sena.crudbasic.controller;

import com.sena.crudbasic.dto.request.UserSystemCreateDTO;
import com.sena.crudbasic.dto.response.UserSystemResponseDTO;
import com.sena.crudbasic.service.IUserSystmsService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserSystmsController {

    private final IUserSystmsService userSystmsService;

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UserSystemResponseDTO> create(@RequestBody UserSystemCreateDTO dto) {
        UserSystemResponseDTO created = userSystmsService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Actualizar usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<UserSystemResponseDTO> update(@PathVariable Integer id,
                                                        @RequestBody UserSystemCreateDTO dto) {
        UserSystemResponseDTO updated = userSystmsService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userSystmsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserSystemResponseDTO> findById(@PathVariable Integer id) {
        UserSystemResponseDTO user = userSystmsService.findById(id);
        return ResponseEntity.ok(user);
    }

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserSystemResponseDTO>> findAll() {
        List<UserSystemResponseDTO> users = userSystmsService.findAll();
        return ResponseEntity.ok(users);
    }

    // Buscar usuario por loginName
    @GetMapping("/by-login/{loginName}")
    public ResponseEntity<UserSystemResponseDTO> findByLoginName(@PathVariable String loginName) {
        UserSystemResponseDTO user = userSystmsService.findByLoginName(loginName);
        return ResponseEntity.ok(user);
    }
}
