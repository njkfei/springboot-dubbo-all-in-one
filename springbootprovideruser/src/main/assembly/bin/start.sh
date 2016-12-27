#!/bin/sh

curdir=`dirname $0`
cd $curdir
curdir=`pwd`

APP_NAME=springboot-provider-user-0.0.1-SNAPSHOT.jar
MAIN_CLASS=com.jinhui.dubbo.SpringbootProviderUserApplication
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

nohup java -classpath $CLASSPATH $MAIN_CLASS &
