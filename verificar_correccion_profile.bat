@echo off
echo ========================================
echo Verificando correccion de ProfileScreen.kt
echo ========================================

echo.
echo Verificando que no hay errores de sintaxis en ProfileScreen.kt...
findstr /n "items(" "app\src\main\java\com\example\myapplication\presentation\screens\profile\ProfileScreen.kt"

if %ERRORLEVEL% == 0 (
    echo.
    echo ADVERTENCIA: Se encontraron usos de items() en ProfileScreen.kt
    echo Esto puede causar errores de compilacion.
) else (
    echo.
    echo OK: No se encontraron usos problemáticos de items() en ProfileScreen.kt
)

echo.
echo Verificando que se usa forEach en lugar de items...
findstr /n "forEach" "app\src\main\java\com\example\myapplication\presentation\screens\profile\ProfileScreen.kt"

if %ERRORLEVEL% == 0 (
    echo.
    echo OK: Se encontro el uso correcto de forEach
) else (
    echo.
    echo ERROR: No se encontro forEach en ProfileScreen.kt
)

echo.
echo ========================================
echo Verificacion completada
echo ========================================
pause




