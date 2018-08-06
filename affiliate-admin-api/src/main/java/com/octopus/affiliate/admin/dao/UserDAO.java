/*
 * Copyright (c) 2017 Wang Qiuyi. All Rights Reserved.
 */
package com.octopus.affiliate.admin.dao;

import com.octopus.affiliate.model.Role;
import com.octopus.affiliate.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * UserDAO
 * 
 * @author qiuyi.wang
 * @version qiuyi.wang Initial Created at 2017年10月26日 下午4:37:32
 */
@Mapper
public interface UserDAO {

    @Insert("INSERT INTO affiliate_user(name, password, email, phone, qq, role, status) VALUES(#{name}, #{password}, #{email}, #{phone}, #{qq}, #{role}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int register(User user);

    @Update("UPDATE affiliate_user SET l_time=#{lTime} WHERE email=#{email}")
    long login(User user);

    @Select("SELECT * FROM affiliate_user WHERE email=#{email}")
    User getUserByEmail(@Param("email") String email);

    @Select("SELECT * FROM affiliate_user WHERE email=#{email}")
    List<User> getUsersByEmail(@Param("email") String email);

    @Select("SELECT * FROM affiliate_user WHERE email=#{email} AND password=#{password} AND status=1")
    User getUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Select("SELECT * FROM affiliate_user WHERE id=#{id}")
    User getUserById(@Param("id") int id);

    @Select({ //
            "<script>", //
            "SELECT * FROM affiliate_user WHERE id in ",
            "<foreach item='item' index='index' collection='ids'", //
            " open='(' separator=',' close=')'>", //
            " #{item}", //
            "</foreach>", //
            "</script>" //
    })
    List<User> getUsersByIds(@Param("ids") List<Integer> ids);

    @Select({ //
            "<script>", //
            "SELECT * FROM affiliate_user WHERE role in ", //
            "<foreach item='item' index='index' collection='roles'", //
            " open='(' separator=',' close=')'>", //
            " #{item}", //
            "</foreach>", //
            "</script>" //
    })
    List<User> getUsers(@Param("roles") List<Role> roles);

    @Update("UPDATE affiliate_user SET u_time=now(), status=0 WHERE id=#{id}")
    long delUserById(@Param("id") int id);

    @Update("UPDATE affiliate_user SET name=#{name}, email=#{email}, phone=#{phone}, role=#{role}, status=#{status} WHERE id=#{id}")
    long updateUser(User user);

    @Select("SELECT accept_status from affiliate_user where id=#{id}")
    int getAcceptStatus(User user);

    @Update("UPDATE affiliate_user SET accept_status=1 where id=#{id}")
    long accept(User user);
}
