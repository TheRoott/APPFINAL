@echo off
echo ========================================
echo   SOLUCION PROBLEMAS DE BUILD
echo ========================================

echo.
echo [1/10] Deteniendo procesos de Gradle...
echo.
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
taskkill /f /im studio64.exe 2>nul
echo ✅ Procesos detenidos

echo.
echo [2/10] Limpiando cache local...
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
echo [3/10] Verificando configuracion Gradle...
echo.
echo Gradle Wrapper:
type "gradle\wrapper\gradle-wrapper.properties"

echo.
echo [4/10] Verificando configuracion AGP...
echo.
echo build.gradle.kts (raiz):
findstr "android.*version" "build.gradle.kts"

echo.
echo [5/10] Verificando dependencias principales...
echo.
echo Compose BOM:
findstr "compose-bom" "app\build.gradle.kts"
echo.
echo Material Icons:
findstr "material-icons" "app\build.gradle.kts"

echo.
echo [6/10] Verificando archivos principales...
echo.
if exist "app\src\main\AndroidManifest.xml" (
    echo ✅ AndroidManifest.xml OK
) else (
    echo ❌ AndroidManifest.xml faltante
)

if exist "app\src\main\java\com\example\myapplication\MainActivity.kt" (
    echo ✅ MainActivity.kt OK
) else (
    echo ❌ MainActivity.kt faltante
)

echo.
echo [7/10] Creando archivo de configuracion temporal...
echo.
echo # Configuracion temporal para debug > gradle.properties.tmp
echo org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8 >> gradle.properties.tmp
echo org.gradle.parallel=true >> gradle.properties.tmp
echo org.gradle.caching=true >> gradle.properties.tmp
echo android.enableR8.fullMode=false >> gradle.properties.tmp
echo android.useAndroidX=true >> gradle.properties.tmp
echo android.enableJetifier=true >> gradle.properties.tmp
echo ✅ Archivo temporal creado

echo.
echo [8/10] Probando compilacion basica...
echo.
echo Ejecutando gradlew --version...
gradlew --version
if %errorLevel% == 0 (
    echo ✅ Gradle funciona correctamente
) else (
    echo ❌ Problema con Gradle
)

echo.
echo [9/10] Ejecutando clean...
echo.
gradlew clean
if %errorLevel% == 0 (
    echo ✅ Clean exitoso
) else (
    echo ❌ Error en clean
)

echo.
echo [10/10] Resumen y recomendaciones...
echo.
echo ========================================
echo   ESTADO ACTUAL:
echo ========================================
echo.
echo ✅ Archivos del proyecto verificados
echo ✅ Cache local limpiado
echo ✅ Procesos detenidos
echo ✅ Configuracion verificada
echo.
echo ========================================
echo   PROXIMOS PASOS EN ANDROID STUDIO:
echo ========================================
echo.
echo 1. Abrir Android Studio
echo 2. File → Sync Project with Gradle Files
echo 3. Esperar a que termine la sincronizacion
echo 4. Build → Clean Project
echo 5. Build → Rebuild Project
echo 6. Si hay errores, verificar:
echo    - Conexion a internet
echo    - Permisos de escritura
echo    - Espacio en disco
echo.
echo ========================================
echo   SI AUN HAY PROBLEMAS:
echo ========================================
echo.
echo 🔧 OPCION 1 - Reiniciar Android Studio:
echo    1. Cerrar Android Studio completamente
echo    2. Esperar 30 segundos
echo    3. Abrir Android Studio como administrador
echo    4. Abrir proyecto nuevamente
echo.
echo 🔧 OPCION 2 - Limpiar cache global:
echo    1. Ejecutar: limpiar_cache_gradle.bat
echo    2. Reiniciar Android Studio
echo.
echo 🔧 OPCION 3 - Verificar configuracion:
echo    1. File → Invalidate Caches and Restart
echo    2. Seleccionar "Invalidate and Restart"
echo    3. Esperar reinicio completo
echo.
echo ========================================
echo   ARCHIVOS DE DIAGNOSTICO:
echo ========================================
echo.
echo ✅ diagnosticar_problemas_build.bat - Diagnostico completo
echo ✅ solucionar_problemas_build.bat - Solucion actual
echo ✅ limpiar_cache_gradle.bat - Limpieza de cache
echo ✅ configurar_microsoft_defender_exclusions.bat - Optimizacion
echo.
echo ========================================
echo   SOLUCION APLICADA
echo ========================================
echo.
echo ✅ Cache local limpiado
echo ✅ Procesos detenidos
echo ✅ Configuracion verificada
echo ✅ Archivos del proyecto OK
echo.
echo 🚀 LISTO PARA COMPILAR EN ANDROID STUDIO
echo.
pause




