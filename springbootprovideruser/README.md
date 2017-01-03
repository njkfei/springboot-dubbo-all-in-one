# dubbo springboot 集成 provider端

## 技术要素
- dubbo2.5.3
- spring4.x
- springboot
- zookeeper

## 亮点
- 闭环java应用
- 代码，配置分离，方便运维开发
- 自带tomcat容器，可以方便扩展
- 自带神器springboot,简化依赖配置

## 工程结构
### 开发结构
![image](http://ww4.sinaimg.cn/large/3d84c572jw1fb55ryw9wej20jp0hlwjc.jpg)

## 部署结构
部署目录结构为：
- bin : 启动，停止脚本
- conf: 配置文件
- lib: 相当jar包
- logs: 系统运行日志

如下图所示：
![image](http://ww3.sinaimg.cn/large/3d84c572jw1fb55vaelkzj20dh0lu0zs.jpg)

## 步骤
### 创建项目
前往[start.spring.io](start.spring.io)创建maven项目．
选上web和acturator即可．

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

### 服务发布
采用注解．考虑代码量和效率并行，使用了部分xml配置．
使用xml配置，主要是为了使用注解．
```
<?xml version="1.0" encoding="UTF-8"?>
<!-- 添加 DUBBO SCHEMA -->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://code.alibabatech.com/schema/dubbo
        http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <dubbo:application name="user-service"/>
    <dubbo:registry address="zookeeper://127.0.0.1:2181" />
    <dubbo:protocol name="dubbo" port="20880" />
    <dubbo:monitor protocol="registry"/>
        <dubbo:annotation package="com.jinhui.dubbo.serviceImpl"/>  <!-- 需要自动打描的包路径 -->
</beans>
```
这里面，最重要的就是自动扫描包的路径．通过他可以自动打待通过dubbo发布的服务．

看下面．
```
import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by niejinping on 2016/12/23.
 */
@Service(version="1.0.0")
public class UserServiceImpl implements UserService {
    // TODO:YOU SREVICE
}
```

springboot导入xml配置．
```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo-provider.xml")
public class SpringbootProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProviderUserApplication.class, args);
	}
}
```
springboot通过@ImportResource注解，即可完成xml配置的导入．
```
@ImportResource("classpath:dubbo-provider.xml")
```

> 总结一下，启动路径是这样的．springboot -> ImportResources("classpath:dubbo-provider.xml") -> dubbo:annotation指定发布服务的包 -> @Service(version="1.0.0") -> 完成服务发布．

## 坑
### springboot maven插件与jar插件冲突
这两种插件打的包是不一样的．
- springboot 插件打的是fat jar
- jar插件打的可以是小jar包
解决方法：注释掉springboot maven插件．
```
<!-- 这个插件会导致assembly插件导入的包是fat jar包，所以我注释掉了-->
<!--			<plugin>
                         <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-maven-plugin</artifactId>
                        </plugin>-->
```
### 启动脚本
fat jar可以直接使用
````
java -jar xxx.jar
```
运行．
但是，当将conf独立出来时，无法识别conf下面的xml配置文件．只能识别conf/aplication.properties文件．
所以，测试的时候，如果有一个conf/application.properties文件．

```
java -jar xxx.jar spring.config.location=../conf/
```
是没有问题的．
但是，如果需要将dubbo-provider.xml放在conf目录下，结果是什么呢？
```
org.springframework.beans.factory.BeanInitializationException: Could not load properties; nested exception is java.io.FileNotFoundException: class path resource [dubbo.properties] cannot be opened because it does not exist
	at org.springframework.context.support.PropertySourcesPlaceholderConfigurer.postProcessBeanFactory(PropertySourcesPlaceholderConfigurer.java:148)
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:281)
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:161)
	at org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:686)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:524)
	at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.refresh(EmbeddedWebApplicationContext.java:122)
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:761)
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:371)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:315)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1186)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1175)
	at com.jinhui.springboot.dubbo.consumer.SpringbootConsumerUserApplication.main(SpringbootConsumerUserApplication.java:38)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:48)
	at org.springframework.boot.loader.Launcher.launch(Launcher.java:87)
	at org.springframework.boot.loader.Launcher.launch(Launcher.java:50)
	at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:51)
Caused by: java.io.FileNotFoundException: class path resource [dubbo.properties] cannot be opened because it does not exist
	at org.springframework.core.io.ClassPathResource.getInputStream(ClassPathResource.java:172)
	at org.springframework.core.io.support.EncodedResource.getInputStream(EncodedResource.java:154)
	at org.springframework.core.io.support.PropertiesLoaderUtils.fillProperties(PropertiesLoaderUtils.java:98)
	at org.springframework.core.io.support.PropertiesLoaderSupport.loadProperties(PropertiesLoaderSupport.java:175)
	at org.springframework.core.io.support.PropertiesLoaderSupport.mergeProperties(PropertiesLoaderSupport.java:156)
	at org.springframework.context.support.PropertySourcesPlaceholderConfigurer.postProcessBeanFactory(PropertySourcesPlaceholderConfigurer.java:139)
	... 19 common frames omitted
```
没错，找不到文件．

> 解决办法

- 改assembly.xml
- 改maven-jar-plugin
- 改maven-assembly-plugin
