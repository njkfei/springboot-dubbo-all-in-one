package com.jinhui.springxml.consumer;
import com.jinhui.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * Created by niejinping on 2016/12/23.
 */

public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String args[]) {
        // ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        App p = new App();
        p.start();
    }

    private void start() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AutowireCapableBeanFactory acbFactory = ctx.getAutowireCapableBeanFactory();
        acbFactory.autowireBean(this);

        // 通过混合注解和xml配置拿到bean
        UserServiceClient userServiceClient = (UserServiceClient)ctx.getBean("userServiceClient");
        UserService userService =userServiceClient.userService;
        userService.saveUser(1,"hello,dubbo with xml and annotation config");
        System.out.println(userService.getUser(1).getUserName());

        // 完全通过xml配置拿到bean
        UserService userServiceWithBean =(UserService)ctx.getBean(UserService.class);
        userService.saveUser(2,"hello,dubbo with xml config bean");
        System.out.println(userServiceWithBean.getUser(2).getUserName());

    }
}
