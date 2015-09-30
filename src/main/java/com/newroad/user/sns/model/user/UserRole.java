package com.newroad.user.sns.model.user;

public enum UserRole {
  ADMIN(1, "admin"), USER(2, "user"), LUCKER(3, "lucker");

  private Integer userRole;

  private String roleName;

  UserRole(Integer userRole, String roleName) {
    this.userRole = userRole;
    this.roleName = roleName;
  }

  public Integer getUserRole() {
    return userRole;
  }

  public void setUserRole(Integer userRole) {
    this.userRole = userRole;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
  
  
}
