package com.jinhui.springboot.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jinhui.api.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * Created by niejinping on 2017/1/4.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference(version="1.0.0",filter="TraceConsumerFilter")
    public  UserService userService;

   @RequestMapping("/{id}")
    private String getUserName(@PathVariable("id")int id){
       return userService.getUserName(id);
   }

}
