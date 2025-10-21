@echo off
echo ========================================
echo ELIMINANDO BUILDCONFIG DUPLICADOS
echo ========================================

echo.
echo Deteniendo procesos de Gradle...
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul

echo.
echo Eliminando archivos BuildConfig específicos...
if exist "app\build\tmp\kotlin-classes\debug\com\example\myapplication\BuildConfig.class" (
    echo Eliminando BuildConfig de kotlin-classes...
    del /f /q "app\build\tmp\kotlin-classes\debug\com\example\myapplication\BuildConfig.class" 2>nul
)

if exist "app\build\intermediates\javac\debug\classes\com\example\myapplication\BuildConfig.class" (
    echo Eliminando BuildConfig de javac classes...
    del /f /q "app\build\intermediates\javac\debug\classes\com\example\myapplication\BuildConfig.class" 2>nul
)

echo.
echo Eliminando directorios completos de build...
if exist "app\build\tmp" (
    echo Eliminando app\build\tmp...
    rmdir /s /q "app\build\tmp" 2>nul
)

if exist "app\build\intermediates" (
    echo Eliminando app\build\intermediates...
    rmdir /s /q "app\build\intermediates" 2>nul
)

echo.
echo Eliminando directorio build completo...
if exist "app\build" (
    echo Eliminando app\build...
    rmdir /s /q "app\build" 2>nul
)

echo.
echo Verificando eliminacion...
if not exist "app\build" echo ✅ app\build eliminado
if exist "app\build" echo ❌ app\build aun existe

echo.
echo ========================================
echo LIMPIEZA COMPLETADA
echo ========================================
echo.
echo Ahora en Android Studio:
echo 1. Build → Clean Project
echo 2. Build → Rebuild Project
echo.
pause




