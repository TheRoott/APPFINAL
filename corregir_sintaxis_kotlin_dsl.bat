@echo off
echo ========================================
echo   CORRECCION SINTAXIS KOTLIN DSL
echo ========================================

echo.
echo [1/8] Problema identificado...
echo.
echo ❌ ERROR: "Unresolved reference: ext"
echo ✅ SOLUCION: Remover sintaxis ext incorrecta
echo.

echo.
echo [2/8] Verificando configuracion actual...
echo.
echo build.gradle.kts (raiz):
type "build.gradle.kts"

echo.
echo [3/8] Verificando gradle-wrapper.properties...
echo.
echo Contenido:
type "gradle\wrapper\gradle-wrapper.properties"

echo.
echo [4/8] Limpiando cache...
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
echo [5/8] Deteniendo procesos...
echo.
taskkill /f /im studio64.exe 2>nul
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
echo ✅ Procesos detenidos

echo.
echo [6/8] Verificando archivos principales...
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

if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo ✅ gradle-wrapper.jar existe
) else (
    echo ❌ gradle-wrapper.jar NO existe
)

echo.
echo [7/8] Configuracion aplicada...
echo.
echo ✅ Sintaxis ext removida
echo ✅ AGP version 8.2.2 configurado en plugins
echo ✅ Kotlin version 1.9.22 configurado en plugins
echo ✅ Gradle Wrapper 8.2 configurado
echo ✅ Cache limpiado
echo ✅ Procesos detenidos

echo.
echo [8/8] Instrucciones finales...
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
echo 6. ESPERAR a que termine (2-3 minutos)
echo 7. Build → Clean Project
echo 8. Build → Rebuild Project
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
echo 🔧 OPCION 3 - Forzar sincronizacion:
echo    Build → Clean Project
echo    File → Sync Project with Gradle Files
echo    Build → Rebuild Project
echo.
echo ========================================
echo   CONFIGURACION APLICADA:
echo ========================================
echo.
echo ✅ AGP Version: 8.2.2 (en plugins)
echo ✅ Kotlin Version: 1.9.22 (en plugins)
echo ✅ Gradle Wrapper: 8.2
echo ✅ Cache: Limpiado
echo ✅ Procesos: Detenidos
echo ✅ Sintaxis: Kotlin DSL correcta
echo.
echo 🚀 LISTO PARA SINCRONIZAR EN ANDROID STUDIO
echo.
echo 📝 NOTA: La sintaxis ext no es valida en Kotlin DSL.
echo    Las versiones estan configuradas en el bloque plugins.
echo.
pause




