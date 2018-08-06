/*
 * Copyright (c) 2017 Wang Qiuyi. All Rights Reserved.
 */
package com.octopus.affiliate.model;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Role
 * 
 * @author qiuyi.wang
 * @version qiuyi.wang Initial Created at 2017年2月25日 上午10:46:20
 */
public enum Role {

    ADMIN(1, "管理员"), 
    BD(2, "销售，对接广告主获取Campaigns"), 
    PM(3, "运营，运营系统平台的Campaigns"), 
    AM(4,"Publisher Manager,渠道经理。管理Publisher"),
    FA(5, "财务，看报表收入等情况"),
    PUBLISHER(6, "渠道");
    
    public static final List<Role> MANAGER_ROLES = Lists.newArrayList(Role.BD, Role.PM, Role.AM, Role.FA);
    
    public static final List<Role> ALL_ROLES = Lists.newArrayList(Role.BD, Role.PM, Role.AM, Role.FA, Role.PUBLISHER);

    private int code;

    private String desc;

    private Role(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Role fromCode(int code) {
        for (Role role : Role.values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }

    public static void main(String[] args) {
        System.out.println(Role.ADMIN);
    }
}
