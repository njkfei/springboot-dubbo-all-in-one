
set APP_NAME=springboot-consumer-user-0.0.1-SNAPSHOT.jar
set APP_HOME=..
set MAIN_CLASS=com.jinhui.springboot.dubbo.consumer.SpringbootConsumerUserApplication

set LIB_PATH=%APP_HOME%\lib

cd ..\bin
set CLASSPATH=%APP_HOME%\conf;%LIB_PATH%\%APP_NAME%

cd %APP_HOME%/bin


java  -classpath %CLASSPATH% %MAIN_CLASS%
