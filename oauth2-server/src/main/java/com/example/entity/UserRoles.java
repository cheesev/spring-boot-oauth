package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserRoles {

    @Id
    String username;

    String role;

    public UserRoles(){}

}