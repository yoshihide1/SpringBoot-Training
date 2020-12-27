package com.example.training.admin.domain;

import lombok.Data;

@Data
public class Admin {
  public static final String SESSION_NAME = "ADMIN";
  private int id;
  private String name;
  private String password;
  private String roles = "ROLE_USER,ROLE_ADMIN";

  public Admin() {

  }

  public Admin(int id, String name, String password, String roles) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.roles = roles;
  }

}
