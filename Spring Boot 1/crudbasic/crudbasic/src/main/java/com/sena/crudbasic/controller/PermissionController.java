package com.sena.crudbasic.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sena.crudbasic.dto.request.PermissionCreateDTO;
import com.sena.crudbasic.dto.response.PermissionResponseDTO;
import com.sena.crudbasic.dto.view.JsonViews;
import com.sena.crudbasic.service.IPermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@Tag(name = "Permissions", description = "API para gestión de permisos")
public class PermissionController {

    private final IPermissionService permissionService;

    // Obtener todos los permisos
    @GetMapping
    @JsonView(JsonViews.Resumen.class)
    public ResponseEntity<List<PermissionResponseDTO>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.findAll());
    }

    // Obtener permiso por ID
    @GetMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<PermissionResponseDTO> getPermissionById(@PathVariable Integer id) {
        return ResponseEntity.ok(permissionService.findById(id));
    }

    // Crear permiso
    @PostMapping
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<PermissionResponseDTO> createPermission(
            @Valid @RequestBody PermissionCreateDTO dto) {
        // Usamos save() que viene de GenericServiceImpl
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(permissionService.save(dto));
    }

    // Actualizar permiso
    @PutMapping("/{id}")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<PermissionResponseDTO> updatePermission(
            @PathVariable Integer id,
            @Valid @RequestBody PermissionCreateDTO dto) {
        return ResponseEntity.ok(permissionService.update(id, dto));
    }

    // Eliminar permiso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Integer id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar permiso por nombre
    @GetMapping("/search")
    @JsonView(JsonViews.Detalle.class)
    public ResponseEntity<PermissionResponseDTO> getPermissionByName(@RequestParam String name) {
        return ResponseEntity.ok(permissionService.findByPermissionName(name));
    }

    // Listar permisos activos (si la entidad tuviera un campo active)
    @GetMapping("/active")
    @JsonView(JsonViews.Resumen.class)
    public ResponseEntity<List<PermissionResponseDTO>> getActivePermissions() {
        return ResponseEntity.ok(permissionService.findByActiveTrue());
    }
}
