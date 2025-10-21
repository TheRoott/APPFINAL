@echo off
echo ========================================
echo VERIFICANDO CORRECCION DE @Composable
echo ========================================

echo.
echo Verificando que LaunchedEffect esté fuera de la lambda...
findstr /n "LaunchedEffect(isSubmitting)" "app\src\main\java\com\example\myapplication\presentation\screens\report\ReportScreen.kt" >nul
if %ERRORLEVEL% == 0 (
    echo ✅ LaunchedEffect movido fuera de la lambda
) else (
    echo ❌ LaunchedEffect aún está en la lambda
)

echo.
echo Verificando que no haya LaunchedEffect dentro de funciones lambda...
findstr /n "val.*: () -> Unit.*LaunchedEffect" "app\src\main\java\com\example\myapplication\presentation\screens\report\ReportScreen.kt" >nul
if %ERRORLEVEL% == 1 (
    echo ✅ No hay LaunchedEffect dentro de lambdas
) else (
    echo ❌ Aún hay LaunchedEffect dentro de lambdas
)

echo.
echo Verificando estructura correcta del código...
findstr /n "// LaunchedEffect para manejar" "app\src\main\java\com\example\myapplication\presentation\screens\report\ReportScreen.kt" >nul
if %ERRORLEVEL% == 0 (
    echo ✅ Estructura correcta implementada
) else (
    echo ❌ Estructura no implementada correctamente
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




