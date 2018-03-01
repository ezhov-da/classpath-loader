@echo off

SET PATH_TO_FOLDER_LOAD="%~dp0\examples0;%~dp0\examples1"

for /f %%A in ('java -cp %~dp0\target\classpath-loader.jar ru.ezhov.classpathloader.App %PATH_TO_FOLDER_LOAD% true' ) DO CALL %~dp0\addpath.bat "%%A"

SET JAR_CLASSPATH=%TMP_CP%

echo "JAR_CLASSPATH=%JAR_CLASSPATH%"

pause