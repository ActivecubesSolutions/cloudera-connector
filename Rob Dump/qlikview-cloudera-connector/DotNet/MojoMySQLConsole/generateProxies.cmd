@echo off

echo *** Copying JNI4Net binaries etc. *******
copy C:\Users\mojotech\Projects\Toolkits\JNI4Net\BIN\lib\*.*  C:\Users\mojotech\Projects\Code\DotNet\MojoMySQLConsole\work
PAUSE

echo *** Copying custom JAR file *******
copy C:\Users\mojotech\Projects\Code\Java\MojoMySQLTest.jar C:\Users\mojotech\Projects\Code\DotNet\MojoMySQLConsole\work
PAUSE

echo *** Generating proxy source files *******
C:\Users\mojotech\Projects\Toolkits\JNI4Net\BIN\bin\proxygen.exe C:\Users\mojotech\Projects\Code\DotNet\MojoMySQLConsole\work\MojoMySQLTest.jar -wd C:\Users\mojotech\Projects\Code\DotNet\MojoMySQLConsole\work
PAUSE

echo *** Building proxy binaries *******
cd C:\Users\mojotech\Projects\Code\DotNet\MojoMySQLConsole\work
call build.cmd
cd ..


