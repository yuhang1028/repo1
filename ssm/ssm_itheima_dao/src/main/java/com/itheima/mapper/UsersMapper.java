package com.itheima.mapper;

import com.itheima.domain.PageBean;
import com.itheima.domain.Users;

import java.util.List;

public interface UsersMapper {
    Users login(Users users);

    List<Users> findAll();

    Users findById(Integer id);

    void updateUserMsg(Users users);

    void delete(Integer id);

    void addUser(Users users);

    int totalCount(PageBean pb);

    List<Users> findByPage(PageBean pb);
}
