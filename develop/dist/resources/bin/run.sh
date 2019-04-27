export APP_HOME=./
export JAVA_CMD=$JAVA_HOME/bin/java.exe
export _CLASSPATH="$APP_HOME/conf:$APP_HOME/lib/*"
export JAVA_OPTS="-Xms512m -Xmx1024m"
echo Starting ConsoleService ........
echo Using Java Home $_CLASSPATH
$JAVA_CMD $JAVA_OPTS -DAPP_HOME=$APP_HOME -classpath $_CLASSPATH org.learn.geode.common.console.ConsoleRunner "$@"