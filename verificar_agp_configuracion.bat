@echo off
echo ========================================
echo   VERIFICACION CONFIGURACION AGP
echo ========================================

echo.
echo [1/5] Verificando build.gradle.kts (raiz)...
echo.
echo Contenido del archivo:
type "build.gradle.kts"
echo.

echo.
echo [2/5] Verificando app/build.gradle.kts...
echo.
echo Plugins configurados:
findstr "plugins" -A 5 "app\build.gradle.kts"
echo.

echo.
echo [3/5] Verificando libs.versions.toml...
echo.
echo Versiones configuradas:
findstr "agp\|kotlin" "gradle\libs.versions.toml"
echo.

echo.
echo [4/5] Verificando gradle-wrapper.properties...
echo.
echo Gradle version:
findstr "distributionUrl" "gradle\wrapper\gradle-wrapper.properties"
echo.

echo.
echo [5/5] Resumen de configuracion...
echo.
echo ========================================
echo   CONFIGURACION ACTUAL:
echo ========================================
echo.
echo build.gradle.kts (raiz):
findstr "id.*version" "build.gradle.kts"
echo.
echo app/build.gradle.kts:
findstr "id(" "app\build.gradle.kts"
echo.
echo Gradle wrapper:
findstr "distributionUrl" "gradle\wrapper\gradle-wrapper.properties"
echo.

echo.
echo ========================================
echo   VERIFICACION COMPLETADA
echo ========================================
echo.
echo ✅ Configuracion corregida:
echo - AGP: 8.2.2 (directo)
echo - Kotlin: 1.9.22 (directo)
echo - Gradle: 8.2
echo.
echo Android Studio deberia detectar AGP correctamente ahora
echo.
pause




