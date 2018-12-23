package com.itheima.service.impl;

import com.itheima.domain.PageBean;
import com.itheima.domain.Users;
import com.itheima.mapper.UsersMapper;
import com.itheima.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users login(Users users) {
       Users u = usersMapper.login(users);
       return u;
    }

    @Override
    public List<Users> findAll() {
       List<Users> u = usersMapper.findAll();
        return u;
    }

    @Override
    public Users findById(Integer id) {
       return usersMapper.findById(id);
    }

    @Override
    public void updateUserMsg(Users users) {
        usersMapper.updateUserMsg(users);
    }

    @Override
    public void delete(Integer id) {
        usersMapper.delete(id);
    }

    @Override
    public void addUser(Users users) {
        usersMapper.addUser(users);
    }

    @Override
    public PageBean findByPage(String _currentPage,String _pageRows,PageBean pb) {
        //当前页
       int currentPage = 1;
        try {
            currentPage = Integer.parseInt(_currentPage);
        } catch (NumberFormatException e) {
            e.fillInStackTrace();
        }
        //每页显示的条数
        int pageRows = 5;
        try {
          pageRows = Integer.parseInt(_pageRows);
        }catch (Exception e){}
        //查询用户总数
        int totalCount = usersMapper.totalCount(pb);
        //计算开始查询的索引
        int start = (currentPage-1)*pageRows;
        //封装页面信息
//        PageBean pb = new PageBean();
        pb.setStart(start);
        pb.setPageRows(pageRows);
        List<Users> list= usersMapper.findByPage(pb);
        for (Users users : list) {
            System.out.println(users);
        }
       pb.setList(list);
       pb.setTotalCount(totalCount);
       int count = (int) Math.ceil(totalCount*1.0 /5);
       pb.setTotalPage(count);
       pb.setCurrentPage(currentPage);
       return pb;
    }

    @Override
    public void deleteMore(String[] uids) {
        for (String u : uids) {
            int i = Integer.parseInt(u);
            delete(i);
        }
    }

}
