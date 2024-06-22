package com.cursosapi.springsecurity.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    ADMINISTRATOR(Arrays.asList(
            RolePermissionEnum.READ_ALL_PRODUCTS,
            RolePermissionEnum.UPDATE_ONE_CATEGORY,
            RolePermissionEnum.READ_ONE_CATEGORY,
            RolePermissionEnum.CREATE_ONE_CATEGORY,
            RolePermissionEnum.UPDATE_ONE_CATEGORY,
            RolePermissionEnum.DISABLE_ONE_CATEGORY,
            RolePermissionEnum.READ_MY_PROFILE


    )),
    ASSISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermissionEnum.READ_ALL_PRODUCTS,
            RolePermissionEnum.UPDATE_ONE_CATEGORY,
            RolePermissionEnum.READ_ONE_CATEGORY,
            RolePermissionEnum.CREATE_ONE_CATEGORY,
            RolePermissionEnum.UPDATE_ONE_CATEGORY,
            RolePermissionEnum.READ_MY_PROFILE
    )),
    CUSTOMER(List.of(
            RolePermissionEnum.READ_MY_PROFILE
    ));

    private List<RolePermissionEnum> permission;
    RoleEnum(List<RolePermissionEnum> permission){
        this.permission = permission;
    }

    public List<RolePermissionEnum> getPermission() {
        return permission;
    }

    public void setPermission(List<RolePermissionEnum> permission) {
        this.permission = permission;
    }
}
