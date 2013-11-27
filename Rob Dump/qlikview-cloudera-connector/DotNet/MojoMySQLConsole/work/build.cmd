@echo off
if not exist target mkdir target
if not exist target\classes mkdir target\classes


echo compile classes
javac -nowarn -d target\classes -sourcepath jvm -cp "C:\Users\mojotech\Projects\Code\DotNet\MojoMySQLConsole\work\MojoMySQLTest.jar";"c:\users\mojotech\projects\toolkits\jni4net\bin\lib\jni4net.j-0.8.6.0.jar"; "jvm\mojomysql\ISimpleMySQLDriver_.java" "jvm\mojomysql\StackTraceUtil_.java" "jvm\mojomysql\SimpleMySQLDriver_.java" 
IF %ERRORLEVEL% NEQ 0 goto end


echo MojoMySQLTest.j4n.jar 
jar cvf MojoMySQLTest.j4n.jar  -C target\classes "mojomysql\ISimpleMySQLDriver_.class"  -C target\classes "mojomysql\__ISimpleMySQLDriver.class"  -C target\classes "mojomysql\StackTraceUtil_.class"  -C target\classes "mojomysql\SimpleMySQLDriver_.class"  > nul 
IF %ERRORLEVEL% NEQ 0 goto end


echo MojoMySQLTest.j4n.dll 
csc /nologo /warn:0 /t:library /out:MojoMySQLTest.j4n.dll /recurse:clr\*.cs  /reference:"C:\Users\mojotech\Projects\Toolkits\JNI4Net\BIN\lib\jni4net.n-0.8.6.0.dll"
IF %ERRORLEVEL% NEQ 0 goto end


:end
