@echo off
if not exist target mkdir target
if not exist target\classes mkdir target\classes


echo compile classes
javac -nowarn -d target\classes -sourcepath jvm -cp "D:\projects\ontometrics\qlikview-cloudera-connector-texus\DotNet\QvBigDataConn\work\MojoHiveDriver.jar";"d:\projects\ontometrics\qlikview-cloudera-connector-texus\dotnet\libs\jni4net\lib\jni4net.j-0.8.6.0.jar"; "jvm\mojohive\IMojoHiveDriver_.java" "jvm\mojohive\ConnectionParams_.java" "jvm\mojohive\DataSourceFactory_.java" "jvm\mojohive\ModuleConfiguration_.java" "jvm\mojohive\MojoHiveDriver_.java" "jvm\mojohive\MojoHiveMain_.java" "jvm\mojohive\QlikViewMain_.java" "jvm\mojohive\StackTraceUtil_.java" "jvm\mojohive/testmode\EmbeddedH2Database_.java" "jvm\mojohive\xmlUtil_.java" 
IF %ERRORLEVEL% NEQ 0 goto end


echo MojoHiveDriver.j4n.jar 
jar cvf MojoHiveDriver.j4n.jar  -C target\classes "mojohive\IMojoHiveDriver_.class"  -C target\classes "mojohive\__IMojoHiveDriver.class"  -C target\classes "mojohive\ConnectionParams_.class"  -C target\classes "mojohive\DataSourceFactory_.class"  -C target\classes "mojohive\ModuleConfiguration_.class"  -C target\classes "mojohive\MojoHiveDriver_.class"  -C target\classes "mojohive\MojoHiveMain_.class"  -C target\classes "mojohive\QlikViewMain_.class"  -C target\classes "mojohive\StackTraceUtil_.class"  -C target\classes "mojohive\testmode\EmbeddedH2Database_.class"  -C target\classes "mojohive\xmlUtil_.class"  > nul 
IF %ERRORLEVEL% NEQ 0 goto end


echo MojoHiveDriver.j4n.dll 
csc /nologo /warn:0 /t:library /out:MojoHiveDriver.j4n.dll /recurse:clr\*.cs  /reference:"D:\projects\ontometrics\qlikview-cloudera-connector-texus\DotNet\libs\JNI4Net\lib\jni4net.n-0.8.6.0.dll"
IF %ERRORLEVEL% NEQ 0 goto end


:end
