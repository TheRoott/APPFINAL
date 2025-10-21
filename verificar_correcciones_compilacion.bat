@echo off
echo ========================================
echo VERIFICANDO CORRECCIONES DE COMPILACION
echo ========================================

echo.
echo 1. Verificando colores en EnvironmentalAds.kt...
findstr /n "Color(0xFF" "app\src\main\java\com\example\myapplication\presentation\screens\home\EnvironmentalAds.kt" >nul
if %ERRORLEVEL% == 0 (
    echo ✅ Colores corregidos en EnvironmentalAds.kt
) else (
    echo ❌ Aún hay problemas con colores en EnvironmentalAds.kt
)

echo.
echo 2. Verificando colores en RewardsScreen.kt...
findstr /n "Color(0xFF" "app\src\main\java\com\example\myapplication\presentation\screens\rewards\RewardsScreen.kt" >nul
if %ERRORLEVEL% == 0 (
    echo ✅ Colores corregidos en RewardsScreen.kt
) else (
    echo ❌ Aún hay problemas con colores en RewardsScreen.kt
)

echo.
echo 3. Verificando imports en ReportScreen.kt...
findstr /n "import.*sp" "app\src\main\java\com\example\myapplication\presentation\screens\report\ReportScreen.kt" >nul
if %ERRORLEVEL% == 0 (
    echo ✅ Import sp agregado en ReportScreen.kt
) else (
    echo ❌ Falta import sp en ReportScreen.kt
)

echo.
echo 4. Verificando funciones lambda en ReportScreen.kt...
findstr /n "val takePhoto:" "app\src\main\java\com\example\myapplication\presentation\screens\report\ReportScreen.kt" >nul
if %ERRORLEVEL% == 0 (
    echo ✅ Funciones lambda corregidas en ReportScreen.kt
) else (
    echo ❌ Aún hay problemas con funciones en ReportScreen.kt
)

echo.
echo ========================================
echo VERIFICACION COMPLETADA
echo ========================================
echo.
echo Ahora intenta compilar de nuevo:
echo Build → Rebuild Project
echo.
pause




