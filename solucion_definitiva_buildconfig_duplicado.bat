@echo off
echo ========================================
echo SOLUCION DEFINITIVA BUILDCONFIG DUPLICADO
echo ========================================

echo.
echo PASO 1: Deteniendo todos los procesos...
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
taskkill /f /im kotlin.exe 2>nul

echo.
echo PASO 2: Eliminando archivos BuildConfig específicos...
if exist "app\build\tmp\kotlin-classes\debug\com\example\myapplication\BuildConfig.class" (
    echo Eliminando BuildConfig de kotlin-classes...
    del /f /q "app\build\tmp\kotlin-classes\debug\com\example\myapplication\BuildConfig.class" 2>nul
)

if exist "app\build\intermediates\javac\debug\classes\com\example\myapplication\BuildConfig.class" (
    echo Eliminando BuildConfig de javac classes...
    del /f /q "app\build\intermediates\javac\debug\classes\com\example\myapplication\BuildConfig.class" 2>nul
)

echo.
echo PASO 3: Eliminando directorios completos de build...
if exist "app\build" (
    echo Eliminando app\build completamente...
    rmdir /s /q "app\build" 2>nul
    if exist "app\build" (
        echo Intentando con permisos administrativos...
        takeown /f "app\build" /r /d y 2>nul
        icacls "app\build" /grant %USERNAME%:F /t 2>nul
        rmdir /s /q "app\build" 2>nul
    )
)

if exist "build" (
    echo Eliminando build del proyecto...
    rmdir /s /q "build" 2>nul
)

if exist ".gradle" (
    echo Eliminando .gradle...
    rmdir /s /q ".gradle" 2>nul
)

echo.
echo PASO 4: Limpiando cache global de Gradle...
if exist "%USERPROFILE%\.gradle\caches" (
    echo Eliminando cache global de Gradle...
    rmdir /s /q "%USERPROFILE%\.gradle\caches" 2>nul
)

echo.
echo PASO 5: Verificando eliminacion...
if not exist "app\build" echo ✅ app\build eliminado
if not exist "build" echo ✅ build eliminado  
if not exist ".gradle" echo ✅ .gradle eliminado

echo.
echo PASO 6: Verificando configuracion BuildConfig...
findstr /n "buildConfig = true" "app\build.gradle.kts" >nul
if %ERRORLEVEL% == 0 (
    echo ✅ BuildConfig activado en build.gradle.kts
) else (
    echo ❌ BuildConfig no esta activado
)

echo.
echo ========================================
echo LIMPIEZA COMPLETADA
echo ========================================
echo.
echo INSTRUCCIONES CRITICAS:
echo 1. CERRAR Android Studio completamente
echo 2. Esperar 30 segundos
echo 3. Abrir Android Studio
echo 4. Build → Clean Project
echo 5. Build → Rebuild Project
echo.
echo Si el error persiste, ejecutar este script nuevamente
echo.
pause




