# springboot-dubbo全家桶

本项目包括4分部分．
- api接口
- dubbo-provider-with-springboot
- dubbo-consumer-with-springboot
- dubbo-consumer-with-spring3xml

说明：名字和子目录没有完全对上，理解上下文即可．

## 亮点
- 闭环java应用
- 代码，配置分离，方便运维开发
- 自带tomcat容器，可以方便扩展
- 自带神器springboot,简化依赖配置
- 有发布功能哦．通过jgitflow插件完成

## dubbo-provider-with-springboot
这个项目，是服务提供者，基本全部是注解，采用jdk1.7．

### dubbo-consumer-with-springboot
这个项目，是服务消费者，基本全部是注解，采用jdk1.7．

### dubbo-consumer-with-spring3xml
这个项目，是服务消费者，采用jdk1.6．模拟服务提供者是新开发，但服务消费者是老系统的问题．

## 问题
为什么要这么安排？

## 坑
真的很多，看各个子目录下面的readme文件吧．

### 关于release插件
scm插件最开始是配合svn使用的.和git的配合不好，使用git打包时，建议使用jgitflow插件．大厂出口，值得信任．

## 打包release
在develop分支下面．
运行
```
mvn jgitflow:release-start -DreleaseVersion=1.0.0 -DdevelopmentVersion=1.1.0-SNAPSHOT -DpushReleases=true -DallowSnapshots=true
```

```
mvn jgitflow:release-finish -DnoReleaseBuild=true -DnoDeploy=true -DpushReleases=true
```
只需要两个步骤，即可完成release和打tag功能．比scm好用多了．

