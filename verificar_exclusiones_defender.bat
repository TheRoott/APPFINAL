@echo off
echo ========================================
echo   VERIFICACION EXCLUSIONES MICROSOFT DEFENDER
echo ========================================

echo.
echo [1/4] Verificando exclusiones configuradas...
echo.

echo Exclusiones de carpetas:
powershell -Command "try { Get-MpPreference | Select-Object -ExpandProperty ExclusionPath | ForEach-Object { Write-Host \"✅ $_\" } } catch { Write-Host \"❌ No se pueden verificar las exclusiones\" }"

echo.
echo [2/4] Verificando carpetas especificas...
echo.

echo Verificando C:\Users\Fernando\.gradle...
if exist "C:\Users\Fernando\.gradle" (
    echo ✅ Carpeta existe
) else (
    echo ❌ Carpeta no existe
)

echo.
echo Verificando C:\Users\Fernando\AppData\Local\Android\Sdk...
if exist "C:\Users\Fernando\AppData\Local\Android\Sdk" (
    echo ✅ Carpeta existe
) else (
    echo ❌ Carpeta no existe
)

echo.
echo Verificando C:\Users\Fernando\Desktop\alcatras...
if exist "C:\Users\Fernando\Desktop\alcatras" (
    echo ✅ Carpeta existe
) else (
    echo ❌ Carpeta no existe
)

echo.
echo [3/4] Verificando estado de Microsoft Defender...
echo.
powershell -Command "try { $defender = Get-MpComputerStatus; Write-Host \"Estado: $($defender.RealTimeProtectionEnabled)\"; Write-Host \"Antivirus: $($defender.AntivirusEnabled)\" } catch { Write-Host \"❌ No se puede verificar el estado\" }"

echo.
echo [4/4] Recomendaciones...
echo.
echo ========================================
echo   ESTADO ACTUAL:
echo ========================================
echo.
echo Si las exclusiones NO estan configuradas:
echo.
echo 🔧 OPCION 1 - Configuracion Automatica (Recomendado):
echo    1. Haga clic derecho en \"configurar_microsoft_defender_exclusions.bat\"
echo    2. Seleccione \"Ejecutar como administrador\"
echo    3. Siga las instrucciones en pantalla
echo.
echo 🔧 OPCION 2 - Configuracion Manual:
echo    1. Ejecute \"configurar_defender_manual.bat\"
echo    2. Siga las instrucciones paso a paso
echo    3. Configure las exclusiones manualmente
echo.
echo ========================================
echo   CARPETAS QUE DEBEN ESTAR EXCLUIDAS:
echo ========================================
echo.
echo ✅ C:\Users\Fernando\.gradle
echo ✅ C:\Users\Fernando\AppData\Local\Android\Sdk
echo ✅ C:\Users\Fernando\AppData\Local\Google\AndroidStudio2025.1.4
echo ✅ C:\Users\Fernando\Desktop\alcatras
echo.
echo ========================================
echo   BENEFICIOS ESPERADOS:
echo ========================================
echo.
echo 🚀 Compilacion 3-5x mas rapida
echo 🚀 Android Studio mas fluido
echo 🚀 Gradle mas eficiente
echo 🚀 Mejor rendimiento general
echo.
pause




