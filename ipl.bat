@echo off
.\adb\adb shell pm list packages -f > ./in.txt
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_162
.\maven\bin\mvn exec:java -Dexec.mainClass=com.mycompany.installedpackagelist.Main
pause
