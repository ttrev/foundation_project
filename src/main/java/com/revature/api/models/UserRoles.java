package com.revature.api.models;

import java.util.Objects;

public class UserRoles {
    private String roleId;
    private String role;

    public UserRoles(){
        super();
    }

    public UserRoles(String roleId, String role){
        this.roleId = roleId;
        this.role = role;

    }

    public String getRoleId(){
        return roleId;
    }

    public void setRoleId(String roleId){
        this.roleId = roleId;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRole = (UserRoles) o;
        return Objects.equals(roleId, userRole.roleId) && Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode(){
        return Objects.hash(roleId, role);
    }

    @Override
    public String toString(){
        return "userRoles{" +
                "roleId='" + roleId + '\'' +
                ", role='" + role + '\'' +
                '}';




    }

}
