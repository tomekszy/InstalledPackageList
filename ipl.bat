@echo off
.\adb\adb.exe shell pm list packages -f > ./in.txt
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_162
call .\maven\bin\mvn.bat clean > nul
call .\maven\bin\mvn.bat install > nul
call .\maven\bin\mvn.bat exec:java -Dexec.mainClass=com.mycompany.installedpackagelist.Main
pause
