package com.jinhui.api.service;

import com.jinhui.api.module.User;
/**
 * Created by niejinping on 2016/12/23.
 */
public interface UserService {
    User getUser(int userId);
    int saveUser(int userId,String userName);
}
