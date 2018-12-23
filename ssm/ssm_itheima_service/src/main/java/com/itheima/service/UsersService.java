package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.Users;

import java.util.List;

public interface UsersService {
    /**
     * 用户登陆
     * @param users
     * @return
     */
    Users login(Users users);

    /**
     * 查询所有
     * @return
     */
    List<Users> findAll();

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    Users findById(Integer id);

    /**
     * 修改用户信息
     * @param users
     */
    void updateUserMsg(Users users);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 添加用户信息
     * @param users
     */
    void addUser(Users users);

    /**
     * 分页查询
     * @param currentPage
     * @param pageRows
     * @return
     */
    PageBean findByPage(String currentPage,String pageRows,PageBean pageBean);

    /**
     * 批量删除
     * @param uids
     */
    void deleteMore(String[] uids);


}
