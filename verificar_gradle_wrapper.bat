@echo off
echo ========================================
echo   VERIFICACION GRADLE WRAPPER
echo ========================================

echo.
echo [1/5] Verificando archivo gradle-wrapper.properties...
if exist "gradle\wrapper\gradle-wrapper.properties" (
    echo ✅ Archivo encontrado
    echo.
    echo Contenido del archivo:
    type "gradle\wrapper\gradle-wrapper.properties"
    echo.
) else (
    echo ❌ ERROR: Archivo no encontrado
    exit /b 1
)

echo.
echo [2/5] Verificando gradle-wrapper.jar...
if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo ✅ gradle-wrapper.jar encontrado
) else (
    echo ❌ ERROR: gradle-wrapper.jar no encontrado
    exit /b 1
)

echo.
echo [3/5] Verificando estructura de directorios...
if exist "gradle\wrapper" (
    echo ✅ Directorio gradle\wrapper existe
) else (
    echo ❌ ERROR: Directorio gradle\wrapper no existe
    exit /b 1
)

echo.
echo [4/5] Verificando permisos de lectura...
echo Intentando leer el archivo...
type "gradle\wrapper\gradle-wrapper.properties" >nul 2>&1
if %errorlevel% == 0 (
    echo ✅ Archivo se puede leer correctamente
) else (
    echo ❌ ERROR: No se puede leer el archivo
    exit /b 1
)

echo.
echo [5/5] Verificando formato del archivo...
findstr "distributionUrl" "gradle\wrapper\gradle-wrapper.properties" >nul 2>&1
if %errorlevel% == 0 (
    echo ✅ URL de distribución encontrada
) else (
    echo ❌ ERROR: URL de distribución no encontrada
    exit /b 1
)

echo.
echo ========================================
echo   VERIFICACION COMPLETADA
echo ========================================
echo.
echo ✅ Todos los archivos están correctos
echo ✅ El wrapper debería funcionar ahora
echo.
echo Prueba ejecutar:
echo .\gradlew.bat --version
echo.
pause




