package com.sena.crudbasic.controller;

import com.sena.crudbasic.dto.request.UserPermissionCreateDTO;
import com.sena.crudbasic.dto.response.UserPermissionResponseDTO;
import com.sena.crudbasic.service.IUserPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-permissions")
@RequiredArgsConstructor
public class UserPermissionController {

    private final IUserPermissionService userPermissionService;

    @PostMapping
    public ResponseEntity<UserPermissionResponseDTO> create(@Validated @RequestBody UserPermissionCreateDTO dto) {
        UserPermissionResponseDTO created = userPermissionService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserPermissionResponseDTO>> getByUser(@PathVariable Integer userId) {
        List<UserPermissionResponseDTO> list = userPermissionService.findByUserId(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/permission/{permissionId}")
    public ResponseEntity<List<UserPermissionResponseDTO>> getByPermission(@PathVariable Integer permissionId) {
        List<UserPermissionResponseDTO> list = userPermissionService.findByPermissionId(permissionId);
        return ResponseEntity.ok(list);
    }
}
