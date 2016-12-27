set APP_NAME=user-consumer-1.0-SNAPSHOT.jar
set APP_HOME=..
set MAIN_CLASS=com.jinhui.springxml.consumer.App

set LIB_PATH=%APP_HOME%\lib
set CONF_PAHT=..\conf\

cd ..\bin
set CLASSPATH=%APP_HOME%\conf;%LIB_PATH%\%APP_NAME%

cd %APP_HOME%/bin

java  -classpath %CLASSPATH% %MAIN_CLASS%