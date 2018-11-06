@echo off
.\adb\adb.exe shell pm list packages -f > ./in.txt

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

REM setlocal
set file="./in.txt"
set maxbytesize=1000

FOR /F "usebackq" %%A IN ('%file%') DO set size=%%~zA

if %size% LSS %maxbytesize% goto noin

REM if not exist .\in.txt goto noin

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem	  InstalledPackageList v.1.00 by tomekszy
@rem  startup script based on:
@rem  Gradle startup script for Windows
@rem ##########################################################################

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

REM set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

@rem Execute Gradle
REM "%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %CMD_LINE_ARGS%

call .\maven\bin\mvn.bat clean > nul
call .\maven\bin\mvn.bat install > nul
call .\maven\bin\mvn.bat exec:java -Dexec.mainClass=com.mycompany.installedpackagelist.Main
if not exist .\out.csv goto noout
REM echo Press Enter to show list of installed packages
REM pause
start .\out.csv
goto end

:noin
echo Could not download data from phone. Please check:
echo 1) Connectivity (can you see phone memory from computer?)
echo 2) Drivers (is there any information about failure when installing drivers, when phone is connected?)
echo 3) Is ADB Debugging turned on? (Settings -> Developer settings -> ADB Debugging)
goto fail

:noout
echo Could not process data. Please check:
echo 1) Do you have Java installed, and JAVA_HOME folder set to variables?
echo 2) Do yuu have sufficent disk space?
echo 3) Does the program have all permissions granted?
echo 4) Have you seen program errors above?
goto fail

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
pause
if  not "" == "%GRADLE_EXIT_CONSOLE%" exit 1
exit /b 1
goto end

REM set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_162

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd
:mainEnd
if "%OS%"=="Windows_NT" endlocal
:omega

