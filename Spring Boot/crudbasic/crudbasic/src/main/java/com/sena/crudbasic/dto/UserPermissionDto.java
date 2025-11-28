package com.sena.crudbasic.dto;

public class UserPermissionDto {

    private int id;
    private int systemUserId;
    private int permissionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
