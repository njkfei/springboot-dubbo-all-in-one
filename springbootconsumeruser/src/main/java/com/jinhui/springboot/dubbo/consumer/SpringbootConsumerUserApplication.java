package com.jinhui.springboot.dubbo.consumer;

import com.jinhui.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo-consumer.xml")
public class SpringbootConsumerUserApplication implements CommandLineRunner {
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger("springbootconsumer");
    private final UserServiceClient userServiceClient;

	private final UserService userService;

	private final UserService userServiceWithBean;

	@Autowired
	public SpringbootConsumerUserApplication(UserServiceClient userServiceClient,UserService userService) {
		this.userServiceClient = userServiceClient;
		this.userService = userServiceClient.userService;
		this.userServiceWithBean = userService;
	}

    @Override
    public void run(String... args) {


        while (true) {
            try {
                // 通过注解拿到bean
                userService.saveUser(2, "hello,dubbo with springboot and java config");
                log.debug(userService.getUser(2).getUserName());

                // 通过xml拿的bean
                userServiceWithBean.saveUser(3, "hello,dubbo with springboot and java config and xml bean");
                log.debug(userServiceWithBean.getUser(3).getUserName());

                Thread.sleep(10);
            }

            catch(Exception e){
                log.error(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootConsumerUserApplication.class, args);
    }
}
