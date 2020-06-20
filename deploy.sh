# 编译 + 部署 auto_server

# 输入你的环境上的tomcat的全路径
# export TOMCAT_APP_PATH=tomcat 在部署机器上的路径

### base 函数
killTmocat()
{
  pid=`ps -ef|grep tomcat|grep java|awk '{print $2}'`
  echo "tomcat Id list :$pid"
  if [ "$pid" = "" ]
   then
      echo "no tomcat pid alive"
   else
     kill -9 $pid
  fi
}
cd $AUTO_SERVER_PATH/auto_server
mvn clean install

#停止tomcat
killTmocat

#删除原有工程
rm -rf $TOMCAT_APP_PATH/webapps/ROOT
rm -f $TOMCAT_APP_PATH/webapps/ROOT.war
rm -f $TOMCAT_APP_PATH/webapps/plate-0.0.1-SNAPSHOT.war

# 复制原有工程
cp $AUTO_SERVER_PATH/auto_server/target/plate-0.0.1-SNAPSHOT.war $TOMCAT_APP_PATH/webapps/

cd $AUTO_SERVER_PATH/webapps/
mv plate-0.0.1-SNAPSHOT.war ROOT.war

# 启动tomcat
cd $TOMCAT_APP_PATH/
sh bin/startup.sh
