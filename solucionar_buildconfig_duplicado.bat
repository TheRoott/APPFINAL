@echo off
echo ========================================
echo SOLUCIONANDO ERROR DE BUILDCONFIG DUPLICADO
echo ========================================

echo.
echo Deteniendo procesos de Gradle...
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul

echo.
echo Limpiando directorios de build corruptos...
if exist "app\build" (
    echo Eliminando app\build...
    rmdir /s /q "app\build" 2>nul
)

if exist ".gradle" (
    echo Eliminando .gradle...
    rmdir /s /q ".gradle" 2>nul
)

if exist "build" (
    echo Eliminando build...
    rmdir /s /q "build" 2>nul
)

echo.
echo Limpiando cache de Gradle del usuario...
if exist "%USERPROFILE%\.gradle\caches" (
    echo Eliminando cache de Gradle...
    rmdir /s /q "%USERPROFILE%\.gradle\caches" 2>nul
)

echo.
echo Verificando que los directorios se eliminaron...
if not exist "app\build" echo ✓ app\build eliminado
if not exist ".gradle" echo ✓ .gradle eliminado
if not exist "build" echo ✓ build eliminado

echo.
echo ========================================
echo LIMPIEZA COMPLETADA
echo ========================================
echo.
echo Ahora en Android Studio:
echo 1. File → Invalidate Caches and Restart
echo 2. Build → Clean Project
echo 3. Build → Rebuild Project
echo.
pause




