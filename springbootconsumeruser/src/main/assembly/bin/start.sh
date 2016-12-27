#!/bin/sh

curdir=`dirname $0`
cd $curdir
curdir=`pwd`

APP_NAME=springboot-consumer-user-0.0.1-SNAPSHOT.jar
MAIN_CLASS=com.jinhui.springboot.dubbo.consumer.SpringbootConsumerUserApplication
APP_HOME=..

export APP_HOME

LIB_PATH=$APP_HOME/lib
export LIB_PATH

#for i in $LIB_PATH/*
#	do CLASSPATH=$i:$CLASSPATH
#done
CLASSPATH=$LIB_PATH/$APP_NAME
export CLASSPATH

CLASSPATH=$CLASSPATH:$APP_HOME/conf:$APP_HOME
CONF_PAHT=../conf/
export CLASSPATH
export CONF_PATH
cd $APP_HOME/bin

## springboot maven 插件使用这个语句
##nohup java $LIB_PATH/$APP_NAME --spring.config.location=$CONF_PAHT &

nohup java -classpath $CLASSPATH $MAIN_CLASS &