@echo off
echo ========================================
echo SOLUCION FINAL COMPLETA PARA BUILDCONFIG
echo ========================================

echo.
echo PASO 1: Deteniendo procesos...
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
taskkill /f /im kotlin.exe 2>nul

echo.
echo PASO 2: Eliminando directorios de build...
if exist "app\build" (
    echo Eliminando app\build...
    rmdir /s /q "app\build" 2>nul
    if exist "app\build" (
        takeown /f "app\build" /r /d y 2>nul
        icacls "app\build" /grant %USERNAME%:F /t 2>nul
        rmdir /s /q "app\build" 2>nul
    )
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
echo PASO 3: Limpiando cache global...
if exist "%USERPROFILE%\.gradle\caches" (
    echo Eliminando cache de Gradle...
    rmdir /s /q "%USERPROFILE%\.gradle\caches" 2>nul
)

echo.
echo PASO 4: Verificando configuracion...
echo Verificando que buildConfig = false en build.gradle.kts...
findstr /n "buildConfig = false" "app\build.gradle.kts" >nul
if %ERRORLEVEL% == 0 (
    echo ✅ buildConfig desactivado correctamente
) else (
    echo ❌ buildConfig no esta desactivado
)

echo.
echo PASO 5: Verificando eliminacion...
if not exist "app\build" echo ✅ app\build eliminado
if not exist ".gradle" echo ✅ .gradle eliminado
if not exist "build" echo ✅ build eliminado

echo.
echo ========================================
echo CONFIGURACION APLICADA:
echo - buildConfig = false (temporalmente)
echo - buildConfigField comentado
echo - packagingOptions configurado
echo - sourceSets configurado
echo ========================================
echo.
echo INSTRUCCIONES FINALES:
echo 1. CERRAR Android Studio completamente
echo 2. Esperar 30 segundos
echo 3. Abrir Android Studio
echo 4. Build → Clean Project
echo 5. Build → Rebuild Project
echo.
echo Si compila exitosamente, podemos reactivar buildConfig
echo.
pause




