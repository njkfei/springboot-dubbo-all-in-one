package com.jinhui.dubbo.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jinhui.api.module.User;
import com.jinhui.api.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niejinping on 2016/12/23.
 */
@Service(version="1.0.0",filter="TraceProviderFilter")
public class UserServiceImpl implements UserService {
    private Map<Integer,User> userList = new HashMap<Integer,User>();

    public User getUser(int userId){
        User user = userList.get(userId);
        if(user == null){
            return new User(0,"nouserid");
        }
        return user;
    }

    public int saveUser(int userId, String userName){
        System.out.println(userId + userName);
        User user = new User(userId,userName);
        userList.put(userId,user);
        return 1;
    }

    public String getUserName(int userId){
        User user = userList.get(userId);
        if(user == null){
            return new User(0,"nouserid").getUserName();
        }
        return user.getUserName();
    }
}
