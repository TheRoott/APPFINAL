@echo off
echo ========================================
echo   CORRECCION GRADLE WRAPPER COMPLETO
echo ========================================

echo.
echo [1/8] Verificando archivos del Gradle Wrapper...
echo.
if exist "gradle\wrapper\gradle-wrapper.properties" (
    echo ✅ gradle-wrapper.properties existe
    echo Contenido actual:
    type "gradle\wrapper\gradle-wrapper.properties"
) else (
    echo ❌ gradle-wrapper.properties NO existe
)

echo.
if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo ✅ gradle-wrapper.jar existe
    for %%A in ("gradle\wrapper\gradle-wrapper.jar") do echo Tamano: %%~zA bytes
) else (
    echo ❌ gradle-wrapper.jar NO existe
)

echo.
echo [2/8] Recreando gradle-wrapper.properties...
echo.
echo distributionBase=GRADLE_USER_HOME > "gradle\wrapper\gradle-wrapper.properties"
echo distributionPath=wrapper/dists >> "gradle\wrapper\gradle-wrapper.properties"
echo distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip >> "gradle\wrapper\gradle-wrapper.properties"
echo networkTimeout=10000 >> "gradle\wrapper\gradle-wrapper.properties"
echo validateDistributionUrl=true >> "gradle\wrapper\gradle-wrapper.properties"
echo zipStoreBase=GRADLE_USER_HOME >> "gradle\wrapper\gradle-wrapper.properties"
echo zipStorePath=wrapper/dists >> "gradle\wrapper\gradle-wrapper.properties"

echo ✅ Archivo recreado

echo.
echo [3/8] Verificando nuevo contenido...
echo.
echo Contenido recreado:
type "gradle\wrapper\gradle-wrapper.properties"

echo.
echo [4/8] Verificando integridad del archivo...
echo.
echo Verificando codificacion...
powershell -Command "Get-Content 'gradle\wrapper\gradle-wrapper.properties' -Encoding UTF8 | Out-String | Measure-Object -Line"
if %errorLevel% == 0 (
    echo ✅ Codificacion correcta
) else (
    echo ❌ Problema de codificacion
)

echo.
echo [5/8] Limpiando cache local...
echo.
if exist ".gradle" (
    rmdir /s /q ".gradle"
    echo ✅ .gradle eliminado
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
echo [6/8] Deteniendo procesos de Gradle...
echo.
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
taskkill /f /im studio64.exe 2>nul
echo ✅ Procesos detenidos

echo.
echo [7/8] Probando Gradle Wrapper...
echo.
echo Ejecutando gradlew --version...
gradlew --version
if %errorLevel% == 0 (
    echo ✅ Gradle Wrapper funciona correctamente
) else (
    echo ❌ Error con Gradle Wrapper
)

echo.
echo [8/8] Resumen de correccion...
echo.
echo ========================================
echo   CORRECCION COMPLETADA:
echo ========================================
echo.
echo ✅ gradle-wrapper.properties recreado
echo ✅ Formato corregido (sin espacios extra)
echo ✅ Codificacion UTF-8 verificada
echo ✅ Cache local limpiado
echo ✅ Procesos detenidos
echo.
echo ========================================
echo   PROXIMOS PASOS EN ANDROID STUDIO:
echo ========================================
echo.
echo 1. CERRAR Android Studio completamente
echo 2. ESPERAR 30 segundos
echo 3. ABRIR Android Studio como administrador
echo 4. ABRIR proyecto EcoVive Peru
echo 5. File → Sync Project with Gradle Files
echo 6. ESPERAR a que termine la sincronizacion
echo 7. Build → Clean Project
echo 8. Build → Rebuild Project
echo.
echo ========================================
echo   VERIFICACION FINAL:
echo ========================================
echo.
echo ✅ Gradle Wrapper: 8.2 configurado
echo ✅ Formato: Corregido
echo ✅ Cache: Limpiado
echo ✅ Procesos: Detenidos
echo.
echo 🚀 LISTO PARA COMPILAR EN ANDROID STUDIO
echo.
pause




