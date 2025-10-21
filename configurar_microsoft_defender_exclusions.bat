@echo off
echo ========================================
echo   CONFIGURACION MICROSOFT DEFENDER
echo ========================================

echo.
echo [1/6] Verificando permisos de administrador...
echo.
net session >nul 2>&1
if %errorLevel% == 0 (
    echo ✅ Ejecutando como administrador
) else (
    echo ⚠️  Este script necesita permisos de administrador
    echo    Haga clic derecho en el archivo y seleccione "Ejecutar como administrador"
    pause
    exit /b 1
)

echo.
echo [2/6] Configurando exclusiones de Microsoft Defender...
echo.

echo Agregando exclusiones de carpetas...
echo.

echo - C:\Users\Fernando\.gradle
powershell -Command "Add-MpPreference -ExclusionPath 'C:\Users\Fernando\.gradle'"
if %errorLevel% == 0 (
    echo ✅ C:\Users\Fernando\.gradle agregado
) else (
    echo ❌ Error al agregar C:\Users\Fernando\.gradle
)

echo.
echo - C:\Users\Fernando\AppData\Local\Android\Sdk
powershell -Command "Add-MpPreference -ExclusionPath 'C:\Users\Fernando\AppData\Local\Android\Sdk'"
if %errorLevel% == 0 (
    echo ✅ C:\Users\Fernando\AppData\Local\Android\Sdk agregado
) else (
    echo ❌ Error al agregar Android SDK
)

echo.
echo - C:\Users\Fernando\AppData\Local\Google\AndroidStudio2025.1.4
powershell -Command "Add-MpPreference -ExclusionPath 'C:\Users\Fernando\AppData\Local\Google\AndroidStudio2025.1.4'"
if %errorLevel% == 0 (
    echo ✅ Android Studio agregado
) else (
    echo ❌ Error al agregar Android Studio
)

echo.
echo - C:\Users\Fernando\Desktop\alcatras
powershell -Command "Add-MpPreference -ExclusionPath 'C:\Users\Fernando\Desktop\alcatras'"
if %errorLevel% == 0 (
    echo ✅ Proyecto alcatras agregado
) else (
    echo ❌ Error al agregar proyecto
)

echo.
echo [3/6] Agregando exclusiones de extensiones...
echo.
echo Agregando exclusiones para archivos de desarrollo...
powershell -Command "Add-MpPreference -ExclusionExtension '.gradle'"
powershell -Command "Add-MpPreference -ExclusionExtension '.jar'"
powershell -Command "Add-MpPreference -ExclusionExtension '.apk'"
powershell -Command "Add-MpPreference -ExclusionExtension '.dex'"
powershell -Command "Add-MpPreference -ExclusionExtension '.class'"
echo ✅ Extensiones agregadas

echo.
echo [4/6] Agregando exclusiones de procesos...
echo.
powershell -Command "Add-MpPreference -ExclusionProcess 'java.exe'"
powershell -Command "Add-MpPreference -ExclusionProcess 'gradle.exe'"
powershell -Command "Add-MpPreference -ExclusionProcess 'studio64.exe'"
powershell -Command "Add-MpPreference -ExclusionProcess 'adb.exe'"
echo ✅ Procesos agregados

echo.
echo [5/6] Verificando exclusiones configuradas...
echo.
echo Exclusiones de carpetas:
powershell -Command "Get-MpPreference | Select-Object -ExpandProperty ExclusionPath"

echo.
echo Exclusiones de extensiones:
powershell -Command "Get-MpPreference | Select-Object -ExpandProperty ExclusionExtension"

echo.
echo Exclusiones de procesos:
powershell -Command "Get-MpPreference | Select-Object -ExpandProperty ExclusionProcess"

echo.
echo [6/6] Configuracion completada...
echo.
echo ========================================
echo   EXCLUSIONES CONFIGURADAS
echo ========================================
echo.
echo ✅ Carpetas excluidas:
echo    - C:\Users\Fernando\.gradle
echo    - C:\Users\Fernando\AppData\Local\Android\Sdk
echo    - C:\Users\Fernando\AppData\Local\Google\AndroidStudio2025.1.4
echo    - C:\Users\Fernando\Desktop\alcatras
echo.
echo ✅ Extensiones excluidas:
echo    - .gradle, .jar, .apk, .dex, .class
echo.
echo ✅ Procesos excluidos:
echo    - java.exe, gradle.exe, studio64.exe, adb.exe
echo.
echo 🚀 BENEFICIOS:
echo    - Compilacion mas rapida
echo    - Mejor rendimiento de Android Studio
echo    - Gradle mas eficiente
echo    - Menos falsos positivos
echo.
echo 📝 NOTA: Es posible que necesite reiniciar Android Studio
echo    para que los cambios tomen efecto completamente.
echo.
pause



