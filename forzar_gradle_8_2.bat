@echo off
echo ========================================
echo   FORZANDO GRADLE 8.2
echo ========================================

echo.
echo [1/5] Eliminando cache de Gradle 8.13...
if exist "%USERPROFILE%\.gradle\caches\8.13" (
    rmdir /s /q "%USERPROFILE%\.gradle\caches\8.13"
    echo Cache 8.13 eliminado
) else (
    echo Cache 8.13 no encontrado
)

echo.
echo [2/5] Eliminando cache de kotlin-dsl...
if exist "%USERPROFILE%\.gradle\caches\kotlin-dsl" (
    rmdir /s /q "%USERPROFILE%\.gradle\caches\kotlin-dsl"
    echo Cache kotlin-dsl eliminado
) else (
    echo Cache kotlin-dsl no encontrado
)

echo.
echo [3/5] Verificando gradle-wrapper.properties...
if exist "gradle\wrapper\gradle-wrapper.properties" (
    echo Archivo encontrado
    type "gradle\wrapper\gradle-wrapper.properties"
) else (
    echo ERROR: gradle-wrapper.properties no encontrado
)

echo.
echo [4/5] Descargando Gradle 8.2 manualmente...
echo Creando directorio temporal...
mkdir "%USERPROFILE%\.gradle\wrapper\dists\gradle-8.2-bin" 2>nul

echo.
echo [5/5] Verificando configuración final...
echo.
echo ========================================
echo   CONFIGURACIÓN ACTUAL:
echo ========================================
echo.
echo gradle-wrapper.properties:
type "gradle\wrapper\gradle-wrapper.properties"
echo.
echo libs.versions.toml (AGP):
findstr "agp" "gradle\libs.versions.toml"
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




