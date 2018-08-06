package com.octopus.affiliate.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private int id;

    private String name;

    private String password;

    private Role role;

    private String phone = "";

    private String email;

    private String qq = "";

    private int status = 1;

    private Date cTime;

    private Date uTime;

    private Date lTime;
}
