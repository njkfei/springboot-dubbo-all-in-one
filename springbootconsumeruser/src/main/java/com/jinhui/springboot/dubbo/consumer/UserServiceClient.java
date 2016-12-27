package com.jinhui.springboot.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jinhui.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by niejinping on 2016/12/23.
 */
@Service
public class UserServiceClient {
    @Reference(version="1.0.0")
    public  UserService userService;
}
