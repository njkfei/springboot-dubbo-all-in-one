# dubbo springxml 集成 comsumer端

## 技术要素
- dubbo2.5.3
- spring4.x
- spring3 通过xml配置
- zookeeper

## 亮点
- 闭环java应用
- 代码，配置分离，方便运维开发
- 自带tomcat容器，可以方便扩展
- jdk1.6,可与jdk1.7的provider完成rpc功能

## 工程结构
### 开发结构
见我的另外两个项目．

## 部署结构
部署目录结构为：
- bin : 启动，停止脚本
- conf: 配置文件
- lib: 相当jar包
- logs: 系统运行日志


## 步骤
### 创建项目
和普通的spring3项目类似．

### 加入dubbo依赖
加入dubbo依赖，不需要dubbo内部的springframe框架
```
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>dubbo</artifactId>
			<version>2.5.3</version>
			<exclusions>
				<exclusion>
					<artifactId>spring</artifactId>
					<groupId>org.springframework</groupId>
				</exclusion>
			</exclusions>
		</dependency>
```
### 编码代码
实现业务逻辑，大家都懂的哈．

### 服务引用
考虑历史原因，使用xml配置．
```
<?xml version="1.0" encoding="UTF-8"?>
<!-- 添加 DUBBO SCHEMA -->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://code.alibabatech.com/schema/dubbo
        http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <!-- 应用名 -->
    <dubbo:application name="springxml-user-consumer"/>
    <!-- 连接到哪个注册中心（连接到本机的2181端口zookeeper） -->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>
    <!-- 消费方用什么协议获取服务（用dubbo协议在20880端口暴露服务） -->
    <dubbo:protocol port="20880"/>
    <!-- 提供哪些接口给消费者调用 -->
   <dubbo:reference id="user-service" interface="com.jinhui.api.service.UserService" version="1.0.0"/>

    <!-- 扫描注解包路径，多个包用逗号分隔，不填pacakge表示扫描当前ApplicationContext中所有的类 -->
    <dubbo:annotation package="com.jinhui.springxml.consumer" />
</beans>
```
这里面，最重要的就是自动扫描包的路径．通过他可以自动扫描通过dubbo发布的服务．

服务引用具体代码在这里．
```
import com.alibaba.dubbo.config.annotation.Reference;
import com.jinhui.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Created by niejinping on 2016/12/23.
 */
@Component("userServiceClient")
public class UserServiceClient {
    @Reference(version="1.0.0")
    public  UserService userService;
}
```

> 依赖为：applicationContext.xml -> dubbo-consumer.xml -> @Refenrence -> DubboBeans -> OK;
