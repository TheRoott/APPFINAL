@echo off
echo ========================================
echo   LIMPIEZA COMPLETA DE CACHE GRADLE
echo ========================================

echo.
echo [1/6] Deteniendo daemon de Gradle...
call gradlew.bat --stop

echo.
echo [2/6] Limpiando proyecto...
call gradlew.bat clean

echo.
echo [3/6] Limpiando cache de Gradle...
if exist "%USERPROFILE%\.gradle\caches" (
    rmdir /s /q "%USERPROFILE%\.gradle\caches"
    echo Cache de Gradle eliminado
)

echo.
echo [4/6] Limpiando cache de Android...
if exist "%USERPROFILE%\.android\build-cache" (
    rmdir /s /q "%USERPROFILE%\.android\build-cache"
    echo Cache de Android eliminado
)

echo.
echo [5/6] Limpiando build local...
if exist "build" (
    rmdir /s /q "build"
    echo Build local eliminado
)

echo.
echo [6/6] Descargando Gradle correcto...
call gradlew.bat wrapper --gradle-version=8.2

echo.
echo ========================================
echo   LIMPIEZA COMPLETADA
echo ========================================
echo.
echo Ahora ejecuta en Android Studio:
echo 1. File ^>^> Invalidate Caches and Restart
echo 2. File ^>^> Sync Project with Gradle Files
echo.
pause




