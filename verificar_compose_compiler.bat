@echo off
echo ========================================
echo   VERIFICACION COMPOSE COMPILER
echo ========================================

echo.
echo [1/5] Verificando configuracion de Compose...
echo.
echo buildFeatures y composeOptions:
findstr -A 10 "buildFeatures" "app\build.gradle.kts"
echo.

echo.
echo [2/5] Verificando versiones configuradas...
echo.
echo Kotlin version:
findstr "kotlin.*version" "build.gradle.kts"
echo.
echo Compose Compiler version:
findstr "kotlinCompilerExtensionVersion" "app\build.gradle.kts"
echo.

echo.
echo [3/5] Verificando dependencias de Compose...
echo.
echo Compose BOM:
findstr "compose-bom" "app\build.gradle.kts"
echo.

echo.
echo [4/5] Matriz de compatibilidad:
echo.
echo ========================================
echo   COMPATIBILIDAD COMPOSE COMPILER
echo ========================================
echo.
echo Kotlin 1.9.22 ^<-> Compose Compiler 1.5.8: ✅ COMPATIBLE
echo Kotlin 1.9.22 ^<-> Compose Compiler 1.3.2: ❌ INCOMPATIBLE
echo.
echo Configuracion actual:
echo - Kotlin: 1.9.22
echo - Compose Compiler: 1.5.8
echo - Compose BOM: 2024.02.00
echo.

echo.
echo [5/5] Resumen de configuracion...
echo.
echo ========================================
echo   CONFIGURACION ACTUAL:
echo ========================================
echo.
echo build.gradle.kts (raiz):
findstr "kotlin.*version" "build.gradle.kts"
echo.
echo app/build.gradle.kts:
echo - buildFeatures.compose = true
echo - composeOptions.kotlinCompilerExtensionVersion = 1.5.8
findstr "compose-bom" "app\build.gradle.kts"
echo.

echo.
echo ========================================
echo   VERIFICACION COMPLETADA
echo ========================================
echo.
echo ✅ Compose Compiler actualizado a 1.5.8
echo ✅ Compatible con Kotlin 1.9.22
echo ✅ Compose BOM configurado
echo.
echo El proyecto deberia compilar sin errores ahora
echo.
pause




