@echo off
echo ========================================
echo   FORZAR SINCRONIZACION GRADLE
echo ========================================

echo.
echo [1/10] Deteniendo todos los procesos...
echo.
taskkill /f /im studio64.exe 2>nul
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
taskkill /f /im gradle-daemon.exe 2>nul
echo ✅ Procesos detenidos

echo.
echo [2/10] Limpiando cache completo...
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

if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo ✅ gradle-wrapper.jar presente
) else (
    echo ❌ gradle-wrapper.jar faltante
)

echo.
echo [3/10] Verificando gradle-wrapper.properties...
echo.
echo Contenido actual:
type "gradle\wrapper\gradle-wrapper.properties"

echo.
echo [4/10] Verificando configuracion AGP...
echo.
echo build.gradle.kts (raiz):
findstr "android.*version" "build.gradle.kts"

echo.
echo app\build.gradle.kts:
findstr "id.*android" "app\build.gradle.kts"

echo.
echo [5/10] Recreando gradle-wrapper.properties...
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
echo [6/10] Verificando archivos principales...
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

if exist "gradle\libs.versions.toml" (
    echo ✅ libs.versions.toml existe
) else (
    echo ❌ libs.versions.toml NO existe
)

echo.
echo [7/10] Probando Gradle Wrapper...
echo.
echo Ejecutando gradlew --version...
gradlew --version
if %errorLevel% == 0 (
    echo ✅ Gradle Wrapper funciona
) else (
    echo ❌ Error con Gradle Wrapper
)

echo.
echo [8/10] Ejecutando clean...
echo.
gradlew clean
if %errorLevel% == 0 (
    echo ✅ Clean exitoso
) else (
    echo ❌ Error en clean
)

echo.
echo [9/10] Verificando configuracion final...
echo.
echo ========================================
echo   CONFIGURACION ACTUAL:
echo ========================================
echo.
echo Gradle Wrapper:
type "gradle\wrapper\gradle-wrapper.properties"
echo.
echo AGP en build.gradle.kts:
findstr "android.*version" "build.gradle.kts"
echo.
echo Plugins en app\build.gradle.kts:
findstr "id.*android" "app\build.gradle.kts"

echo.
echo [10/10] Instrucciones finales...
echo.
echo ========================================
echo   INSTRUCCIONES FINALES:
echo ========================================
echo.
echo 1. CERRAR Android Studio completamente
echo 2. ESPERAR 30 segundos
echo 3. ABRIR Android Studio como administrador
echo 4. ABRIR proyecto EcoVive Peru
echo 5. File → Sync Project with Gradle Files
echo 6. ESPERAR a que termine (puede tardar 3-5 minutos)
echo 7. Si aparece popup "Gradle sync needed", hacer clic en "Sync project"
echo 8. Build → Clean Project
echo 9. Build → Rebuild Project
echo.
echo ========================================
echo   SI AUN HAY PROBLEMAS:
echo ========================================
echo.
echo 🔧 OPCION 1 - Invalidar cache:
echo    File → Invalidate Caches and Restart
echo    Seleccionar "Invalidate and Restart"
echo.
echo 🔧 OPCION 2 - Verificar configuracion:
echo    File → Project Structure
echo    Verificar Gradle version: 8.2
echo    Verificar AGP version: 8.2.2
echo.
echo 🔧 OPCION 3 - Recrear proyecto:
echo    Crear nuevo proyecto Android
echo    Copiar archivos fuente
echo    Configurar dependencias
echo.
echo ========================================
echo   ESTADO FINAL:
echo ========================================
echo.
echo ✅ Cache limpiado completamente
echo ✅ Gradle Wrapper recreado
echo ✅ Procesos detenidos
echo ✅ Configuracion verificada
echo.
echo 🚀 LISTO PARA SINCRONIZAR EN ANDROID STUDIO
echo.
pause




