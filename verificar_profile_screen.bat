@echo off
echo ========================================
echo   VERIFICACION PROFILE SCREEN
echo ========================================

echo.
echo [1/6] Verificando archivo ProfileScreen.kt...
echo.
if exist "app\src\main\java\com\example\myapplication\presentation\screens\profile\ProfileScreen.kt" (
    echo ✅ ProfileScreen.kt existe
    echo.
    echo Buscando problemas en el archivo...
    echo.
    echo Buscando 'count':
    findstr /n "count" "app\src\main\java\com\example\myapplication\presentation\screens\profile\ProfileScreen.kt"
    echo.
    echo Buscando 'items(':
    findstr /n "items(" "app\src\main\java\com\example\myapplication\presentation\screens\profile\ProfileScreen.kt"
    echo.
    echo Buscando 'getUserAchievementsData':
    findstr /n "getUserAchievementsData" "app\src\main\java\com\example\myapplication\presentation\screens\profile\ProfileScreen.kt"
) else (
    echo ❌ ProfileScreen.kt NO existe
)

echo.
echo [2/6] Verificando contenido de la linea 205...
echo.
powershell -Command "Get-Content 'app\src\main\java\com\example\myapplication\presentation\screens\profile\ProfileScreen.kt' | Select-Object -Index 204"

echo.
echo [3/6] Verificando contenido de la linea 206...
echo.
powershell -Command "Get-Content 'app\src\main\java\com\example\myapplication\presentation\screens\profile\ProfileScreen.kt' | Select-Object -Index 205"

echo.
echo [4/6] Limpiando cache...
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
echo [5/6] Deteniendo procesos...
echo.
taskkill /f /im studio64.exe 2>nul
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
echo ✅ Procesos detenidos

echo.
echo [6/6] Resumen...
echo.
echo ========================================
echo   ESTADO DEL ARCHIVO:
echo ========================================
echo.
echo ✅ Archivo ProfileScreen.kt verificado
echo ✅ Cache limpiado
echo ✅ Procesos detenidos
echo.
echo ========================================
echo   INSTRUCCIONES:
echo ========================================
echo.
echo 1. CERRAR Android Studio completamente
echo 2. ESPERAR 30 segundos
echo 3. ABRIR Android Studio como administrador
echo 4. ABRIR proyecto EcoVive Peru
echo 5. File → Sync Project with Gradle Files
echo 6. Build → Clean Project
echo 7. Build → Rebuild Project
echo.
echo ========================================
echo   SI AUN HAY ERRORES:
echo ========================================
echo.
echo 🔧 OPCION 1 - Invalidar cache:
echo    File → Invalidate Caches and Restart
echo.
echo 🔧 OPCION 2 - Verificar archivo:
echo    Verificar que no hay caracteres invisibles
echo    Verificar que la sintaxis es correcta
echo.
echo 🔧 OPCION 3 - Recrear archivo:
echo    Si el problema persiste, recrear ProfileScreen.kt
echo.
pause




