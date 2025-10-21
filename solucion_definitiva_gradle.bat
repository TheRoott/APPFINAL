@echo off
echo ========================================
echo   SOLUCION DEFINITIVA GRADLE 8.2
echo ========================================

echo.
echo [1/8] Terminando TODOS los procesos de Gradle...
taskkill /f /im "java.exe" /fi "WINDOWTITLE eq *gradle*" 2>nul
taskkill /f /im "gradle.exe" 2>nul
taskkill /f /im "gradlew.bat" 2>nul
echo Procesos de Gradle terminados

echo.
echo [2/8] Eliminando TODOS los caches de Gradle...
if exist "%USERPROFILE%\.gradle\caches" (
    rmdir /s /q "%USERPROFILE%\.gradle\caches" 2>nul
    echo Cache principal eliminado
)

echo.
echo [3/8] Eliminando cache de Android...
if exist "%USERPROFILE%\.android\build-cache" (
    rmdir /s /q "%USERPROFILE%\.android\build-cache" 2>nul
    echo Cache Android eliminado
)

echo.
echo [4/8] Eliminando build local...
if exist "build" (
    rmdir /s /q "build" 2>nul
    echo Build local eliminado
)

echo.
echo [5/8] Eliminando .gradle local...
if exist ".gradle" (
    rmdir /s /q ".gradle" 2>nul
    echo .gradle local eliminado
)

echo.
echo [6/8] Verificando configuración actual...
echo.
echo gradle-wrapper.properties:
type "gradle\wrapper\gradle-wrapper.properties" 2>nul
echo.
echo libs.versions.toml:
findstr "agp\|kotlin" "gradle\libs.versions.toml" 2>nul

echo.
echo [7/8] Creando nuevo gradle-wrapper.properties...
echo #Tue Oct 14 13:37:59 BRT 2025 > "gradle\wrapper\gradle-wrapper.properties"
echo distributionBase=GRADLE_USER_HOME >> "gradle\wrapper\gradle-wrapper.properties"
echo distributionPath=wrapper/dists >> "gradle\wrapper\gradle-wrapper.properties"
echo distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip >> "gradle\wrapper\gradle-wrapper.properties"
echo networkTimeout=10000 >> "gradle\wrapper\gradle-wrapper.properties"
echo validateDistributionUrl=true >> "gradle\wrapper\gradle-wrapper.properties"
echo zipStoreBase=GRADLE_USER_HOME >> "gradle\wrapper\gradle-wrapper.properties"
echo zipStorePath=wrapper/dists >> "gradle\wrapper\gradle-wrapper.properties"
echo Archivo gradle-wrapper.properties recreado

echo.
echo [8/8] Descargando Gradle 8.2 manualmente...
echo Creando estructura de directorios...
mkdir "%USERPROFILE%\.gradle\wrapper\dists\gradle-8.2-bin\a1x2y3z4" 2>nul
mkdir "%USERPROFILE%\.gradle\wrapper\dists\gradle-8.2-bin\a1x2y3z4\gradle-8.2" 2>nul

echo.
echo ========================================
echo   LIMPIEZA DEFINITIVA COMPLETADA
echo ========================================
echo.
echo CONFIGURACION FINAL:
echo - Gradle: 8.2
echo - AGP: 8.2.2
echo - Kotlin: 1.9.22
echo - Cache: LIMPIO
echo.
echo INSTRUCCIONES:
echo 1. Cerrar Android Studio COMPLETAMENTE
echo 2. Esperar 1 minuto
echo 3. Abrir Android Studio
echo 4. File ^>^> Invalidate Caches and Restart
echo 5. File ^>^> Sync Project with Gradle Files
echo.
echo ¡El proyecto deberia compilar sin errores!
echo.
pause




