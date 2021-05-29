package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.bean.UserExample;

import com.atguigu.dao.UserMapper;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public void registUser(User user) {
        mapper.insert(user);

    }

    @Override
    public User login(User user) {
        UserExample example=new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());

        List<User> u=mapper.selectByExample(example);

        if(u.size()>0){
            return u.get(0);
        }

        return null;

    }

    @Override
    public boolean existsUsername(String username) {

        UserExample example=new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> u=mapper.selectByExample(example);
        if(u.size()>0){
            return true;
        }
        return false;
    }
}
