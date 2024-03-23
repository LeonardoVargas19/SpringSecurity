package com.cursosapi.springsecurity.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ROLE_ADMINISTRATOR(Arrays.asList(
            RolePermssion.READ_ALL_PRODUCTS,
            RolePermssion.CREATE_ONE_PRODUCTS,
            RolePermssion.UPDATE_ONE_CATEGORY,
            RolePermssion.DISABLE_ONE_PRODUCTS,
            RolePermssion.READ_ONE_CATEGORY,
            RolePermssion.CREATE_ONE_CATEGORY,
            RolePermssion.UPDATE_ONE_CATEGORY,
            RolePermssion.DISABLE_ONE_CATEGORY,
            RolePermssion.READ_MY_PROFILE


    )),
    ROLE_ASSISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermssion.READ_ALL_PRODUCTS,
            RolePermssion.CREATE_ONE_PRODUCTS,
            RolePermssion.UPDATE_ONE_CATEGORY,
            RolePermssion.READ_ONE_CATEGORY,
            RolePermssion.CREATE_ONE_CATEGORY,
            RolePermssion.UPDATE_ONE_CATEGORY,
            RolePermssion.READ_MY_PROFILE
    )),
    ROLE_CUSTOMER(List.of(
            RolePermssion.READ_MY_PROFILE
    ));

    private List<RolePermssion> permission;
    Role(List<RolePermssion> permission){
        this.permission = permission;
    }

    public List<RolePermssion> getPermission() {
        return permission;
    }

    public void setPermission(List<RolePermssion> permission) {
        this.permission = permission;
    }
}
