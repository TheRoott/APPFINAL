@echo off
echo ========================================
echo   ELIMINACION COMPLETA GRADLE 8.13
echo ========================================

echo.
echo [1/8] Deteniendo todos los procesos de Gradle...
echo.
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
taskkill /f /im gradle-daemon.exe 2>nul
echo ✅ Procesos de Gradle detenidos

echo.
echo [2/8] Eliminando daemon de Gradle...
echo.
if exist "%USERPROFILE%\.gradle\daemon" (
    rmdir /s /q "%USERPROFILE%\.gradle\daemon"
    echo ✅ Daemon de Gradle eliminado
) else (
    echo ℹ️  No hay daemon de Gradle
)

echo.
echo [3/8] Eliminando CACHE COMPLETO de Gradle 8.13...
echo.
if exist "%USERPROFILE%\.gradle\caches\8.13" (
    echo Eliminando: %USERPROFILE%\.gradle\caches\8.13
    rmdir /s /q "%USERPROFILE%\.gradle\caches\8.13"
    echo ✅ Cache Gradle 8.13 eliminado
) else (
    echo ℹ️  No hay cache de Gradle 8.13
)

echo.
echo [4/8] Eliminando transforms de Gradle 8.13...
echo.
if exist "%USERPROFILE%\.gradle\caches\8.13\transforms" (
    echo Eliminando transforms...
    rmdir /s /q "%USERPROFILE%\.gradle\caches\8.13\transforms"
    echo ✅ Transforms eliminados
)

echo.
echo [5/8] Eliminando metadata corrupto...
echo.
for /d %%i in ("%USERPROFILE%\.gradle\caches\8.13\*") do (
    if exist "%%i\metadata.bin" (
        echo Eliminando metadata: %%i
        del /f /q "%%i\metadata.bin" 2>nul
    )
)

echo.
echo [6/8] Limpiando proyecto local...
echo.
if exist ".gradle" (
    rmdir /s /q ".gradle"
    echo ✅ .gradle local eliminado
)
if exist "app\build" (
    rmdir /s /q "app\build"
    echo ✅ app\build eliminado
)
if exist "build" (
    rmdir /s /q "build"
    echo ✅ build eliminado
)

echo.
echo [7/8] Verificando Gradle Wrapper...
echo.
if exist "gradle\wrapper\gradle-wrapper.properties" (
    echo Contenido actual:
    type "gradle\wrapper\gradle-wrapper.properties"
    echo.
    echo ✅ Archivo existe
) else (
    echo ❌ Archivo no existe
)

echo.
echo [8/8] Recreando Gradle Wrapper Properties...
echo.
echo distributionBase=GRADLE_USER_HOME > "gradle\wrapper\gradle-wrapper.properties"
echo distributionPath=wrapper/dists >> "gradle\wrapper\gradle-wrapper.properties"
echo distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip >> "gradle\wrapper\gradle-wrapper.properties"
echo zipStoreBase=GRADLE_USER_HOME >> "gradle\wrapper\gradle-wrapper.properties"
echo zipStorePath=wrapper/dists >> "gradle\wrapper\gradle-wrapper.properties"

echo.
echo ✅ Gradle Wrapper Properties recreado:
type "gradle\wrapper\gradle-wrapper.properties"

echo.
echo ========================================
echo   LIMPIEZA COMPLETA FINALIZADA
echo ========================================
echo.
echo ✅ Gradle 8.13 completamente eliminado
echo ✅ Caches corruptos eliminados
echo ✅ Gradle Wrapper configurado para 8.2
echo.
echo 🚀 PRÓXIMOS PASOS:
echo 1. Cerrar Android Studio completamente
echo 2. Esperar 30 segundos
echo 3. Abrir Android Studio
echo 4. File → Invalidate Caches and Restart
echo 5. File → Sync Project with Gradle Files
echo.
pause




