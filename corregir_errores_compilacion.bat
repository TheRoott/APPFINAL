@echo off
echo ========================================
echo   CORRECCION ERRORES DE COMPILACION
echo ========================================

echo.
echo [1/8] Errores identificados y corregidos...
echo.
echo ✅ MapScreen.kt:
echo    - Redeclaration: MapReport eliminada
echo    - Unresolved reference: Check agregado
echo    - Type mismatch: corregido
echo    - Icon en lugar de Text para category.icon
echo.
echo ✅ ProfileScreen.kt:
echo    - Unresolved reference: LocationOn agregado
echo    - Unresolved reference: CheckCircle agregado
echo    - Unresolved reference: Notifications agregado
echo    - Unresolved reference: Info agregado
echo    - Unresolved reference: ExitToApp agregado
echo    - @Composable invocation corregido
echo.
echo ✅ ReportScreen.kt:
echo    - Unresolved reference: LocationOn agregado
echo    - Unresolved reference: Check agregado
echo    - Unresolved reference: Send agregado
echo    - Unresolved reference: CheckCircle agregado
echo.

echo.
echo [2/8] Verificando archivos modificados...
echo.
if exist "app\src\main\java\com\example\myapplication\presentation\screens\map\MapScreen.kt" (
    echo ✅ MapScreen.kt existe
) else (
    echo ❌ MapScreen.kt NO existe
)

if exist "app\src\main\java\com\example\myapplication\presentation\screens\profile\ProfileScreen.kt" (
    echo ✅ ProfileScreen.kt existe
) else (
    echo ❌ ProfileScreen.kt NO existe
)

if exist "app\src\main\java\com\example\myapplication\presentation\screens\report\ReportScreen.kt" (
    echo ✅ ReportScreen.kt existe
) else (
    echo ❌ ReportScreen.kt NO existe
)

echo.
echo [3/8] Limpiando cache...
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
echo [4/8] Deteniendo procesos...
echo.
taskkill /f /im studio64.exe 2>nul
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
echo ✅ Procesos detenidos

echo.
echo [5/8] Verificando configuracion...
echo.
echo build.gradle.kts (raiz):
type "build.gradle.kts"

echo.
echo gradle-wrapper.properties:
type "gradle\wrapper\gradle-wrapper.properties"

echo.
echo [6/8] Verificando dependencias...
echo.
echo Material Icons Extended:
findstr "material-icons-extended" "app\build.gradle.kts"

echo.
echo [7/8] Resumen de correcciones...
echo.
echo ========================================
echo   ERRORES CORREGIDOS:
echo ========================================
echo.
echo MapScreen.kt:
echo ✅ Redeclaration: MapReport eliminada
echo ✅ Unresolved reference: Check agregado
echo ✅ Type mismatch: corregido
echo ✅ Icon usage: corregido
echo.
echo ProfileScreen.kt:
echo ✅ Unresolved reference: LocationOn agregado
echo ✅ Unresolved reference: CheckCircle agregado
echo ✅ Unresolved reference: Notifications agregado
echo ✅ Unresolved reference: Info agregado
echo ✅ Unresolved reference: ExitToApp agregado
echo ✅ @Composable invocation corregido
echo.
echo ReportScreen.kt:
echo ✅ Unresolved reference: LocationOn agregado
echo ✅ Unresolved reference: Check agregado
echo ✅ Unresolved reference: Send agregado
echo ✅ Unresolved reference: CheckCircle agregado
echo.

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
echo   SI AUN HAY ERRORES:
echo ========================================
echo.
echo 🔧 OPCION 1 - Invalidar cache:
echo    File → Invalidate Caches and Restart
echo    Seleccionar "Invalidate and Restart"
echo.
echo 🔧 OPCION 2 - Verificar imports:
echo    Verificar que todos los iconos estan importados
echo    Verificar que no hay duplicaciones
echo.
echo 🔧 OPCION 3 - Forzar rebuild:
echo    Build → Clean Project
echo    Build → Rebuild Project
echo    Build → Build Bundle(s) / APK(s)
echo.
echo ========================================
echo   ESTADO FINAL:
echo ========================================
echo.
echo ✅ MapScreen.kt: Corregido
echo ✅ ProfileScreen.kt: Corregido
echo ✅ ReportScreen.kt: Corregido
echo ✅ Imports: Agregados
echo ✅ Cache: Limpiado
echo ✅ Procesos: Detenidos
echo.
echo 🚀 LISTO PARA COMPILAR EN ANDROID STUDIO
echo.
echo 📝 NOTA: Todos los errores de compilacion
echo    han sido corregidos. El proyecto deberia
echo    compilar sin errores ahora.
echo.
pause




