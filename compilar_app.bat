@echo off
echo ========================================
echo COMPILANDO ECOVIVE PERU
echo ========================================

echo.
echo Limpiando proyecto...
gradlew.bat clean

echo.
echo Compilando proyecto...
gradlew.bat assembleDebug

if %ERRORLEVEL% == 0 (
    echo.
    echo ========================================
    echo ✅ COMPILACION EXITOSA!
    echo ========================================
    echo.
    echo La aplicacion EcoVive Peru se compilo correctamente.
    echo Puedes instalarla en tu POCO X7 Pro.
    echo.
) else (
    echo.
    echo ========================================
    echo ❌ ERROR EN COMPILACION
    echo ========================================
    echo.
    echo Revisa los errores arriba.
    echo.
)

pause




