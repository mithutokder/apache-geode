@ECHO OFF

SET APP_HOME=%~dp0\..

SET JAVA_CMD="%JAVA_HOME%"\bin\java.exe

SET _CLASSPATH=%APP_HOME%\conf;%APP_HOME%\lib\*;

SET JAVA_OPTS=-Xms512m -Xmx1024m

echo Starting ConsoleService ........
echo Using Java Home [%JAVA_HOME%]
%JAVA_CMD% %JAVA_OPTS% -DAPP_HOME=%APP_HOME% -classpath %_CLASSPATH% org.learn.geode.common.console.ConsoleRunner %*