@echo off
echo ========================================
echo   DIAGNOSTICO PROBLEMAS DE BUILD
echo ========================================

echo.
echo [1/8] Verificando archivos del proyecto...
echo.
if exist "build.gradle.kts" (
    echo ✅ build.gradle.kts existe
) else (
    echo ❌ build.gradle.kts NO existe
)

if exist "app\build.gradle.kts" (
    echo ✅ app\build.gradle.kts existe
) else (
    echo ❌ app\build.gradle.kts NO existe
)

if exist "gradle\wrapper\gradle-wrapper.properties" (
    echo ✅ gradle-wrapper.properties existe
    echo Contenido:
    type "gradle\wrapper\gradle-wrapper.properties"
) else (
    echo ❌ gradle-wrapper.properties NO existe
)

echo.
echo [2/8] Verificando configuracion AGP...
echo.
echo Version AGP en build.gradle.kts (raiz):
findstr "android.*version" "build.gradle.kts"

echo.
echo Configuracion en app\build.gradle.kts:
findstr "compileSdk" "app\build.gradle.kts"
findstr "minSdk" "app\build.gradle.kts"
findstr "targetSdk" "app\build.gradle.kts"

echo.
echo [3/8] Verificando dependencias principales...
echo.
echo Compose BOM:
findstr "compose-bom" "app\build.gradle.kts"
echo.
echo Material Icons:
findstr "material-icons" "app\build.gradle.kts"
echo.
echo MultiDex:
findstr "multidex" "app\build.gradle.kts"

echo.
echo [4/8] Verificando archivos principales...
echo.
if exist "app\src\main\AndroidManifest.xml" (
    echo ✅ AndroidManifest.xml existe
) else (
    echo ❌ AndroidManifest.xml NO existe
)

if exist "app\src\main\java\com\example\myapplication\MainActivity.kt" (
    echo ✅ MainActivity.kt existe
) else (
    echo ❌ MainActivity.kt NO existe
)

if exist "app\src\main\java\com\example\myapplication\ui\theme\Theme.kt" (
    echo ✅ Theme.kt existe
) else (
    echo ❌ Theme.kt NO existe
)

echo.
echo [5/8] Verificando cache de Gradle...
echo.
if exist "%USERPROFILE%\.gradle\caches" (
    echo ✅ Cache de Gradle existe
    echo Tamano aproximado:
    dir "%USERPROFILE%\.gradle\caches" /s /-c | find "bytes"
) else (
    echo ❌ Cache de Gradle NO existe
)

echo.
echo [6/8] Verificando procesos de Gradle...
echo.
tasklist /fi "imagename eq java.exe" 2>nul | find /i "java.exe" >nul
if %errorLevel% == 0 (
    echo ⚠️  Hay procesos Java ejecutandose
    tasklist /fi "imagename eq java.exe"
) else (
    echo ✅ No hay procesos Java ejecutandose
)

echo.
echo [7/8] Verificando permisos de escritura...
echo.
echo Probando escritura en directorio actual...
echo test > test_write.tmp 2>nul
if exist "test_write.tmp" (
    echo ✅ Permisos de escritura OK
    del test_write.tmp
) else (
    echo ❌ Sin permisos de escritura
)

echo.
echo [8/8] Resumen de diagnostico...
echo.
echo ========================================
echo   RESUMEN DEL DIAGNOSTICO:
echo ========================================
echo.
echo Archivos del proyecto:
if exist "build.gradle.kts" (echo ✅ build.gradle.kts) else (echo ❌ build.gradle.kts)
if exist "app\build.gradle.kts" (echo ✅ app\build.gradle.kts) else (echo ❌ app\build.gradle.kts)
if exist "gradle\wrapper\gradle-wrapper.properties" (echo ✅ gradle-wrapper.properties) else (echo ❌ gradle-wrapper.properties)

echo.
echo Archivos principales:
if exist "app\src\main\AndroidManifest.xml" (echo ✅ AndroidManifest.xml) else (echo ❌ AndroidManifest.xml)
if exist "app\src\main\java\com\example\myapplication\MainActivity.kt" (echo ✅ MainActivity.kt) else (echo ❌ MainActivity.kt)

echo.
echo Cache y procesos:
if exist "%USERPROFILE%\.gradle\caches" (echo ✅ Cache Gradle) else (echo ❌ Cache Gradle)

echo.
echo ========================================
echo   POSIBLES SOLUCIONES:
echo ========================================
echo.
echo Si hay archivos faltantes:
echo 1. Verificar que el proyecto se abrio correctamente
echo 2. File → Sync Project with Gradle Files
echo 3. Build → Clean Project
echo.
echo Si hay problemas de cache:
echo 1. Cerrar Android Studio
echo 2. Ejecutar: limpiar_cache_gradle.bat
echo 3. Abrir Android Studio nuevamente
echo.
echo Si hay problemas de permisos:
echo 1. Ejecutar Android Studio como administrador
echo 2. Verificar que no hay archivos bloqueados
echo.
echo Si hay procesos Java ejecutandose:
echo 1. Cerrar Android Studio completamente
echo 2. Terminar procesos Java manualmente
echo 3. Abrir Android Studio nuevamente
echo.
pause




