@echo off
echo ========================================
echo SOLUCION DEFINITIVA PARA BUILDCONFIG DUPLICADO
echo ========================================

echo.
echo PASO 1: Deteniendo TODOS los procesos...
taskkill /f /im java.exe 2>nul
taskkill /f /im gradle.exe 2>nul
taskkill /f /im kotlin.exe 2>nul
taskkill /f /im javac.exe 2>nul

echo.
echo PASO 2: Eliminando directorios de build...
if exist "app\build" (
    echo Eliminando app\build...
    rmdir /s /q "app\build" 2>nul
    if exist "app\build" (
        echo Forzando eliminacion con takeown...
        takeown /f "app\build" /r /d y 2>nul
        icacls "app\build" /grant administrators:F /t 2>nul
        rmdir /s /q "app\build" 2>nul
    )
)

if exist ".gradle" (
    echo Eliminando .gradle...
    rmdir /s /q ".gradle" 2>nul
    if exist ".gradle" (
        takeown /f ".gradle" /r /d y 2>nul
        icacls ".gradle" /grant administrators:F /t 2>nul
        rmdir /s /q ".gradle" 2>nul
    )
)

if exist "build" (
    echo Eliminando build...
    rmdir /s /q "build" 2>nul
    if exist "build" (
        takeown /f "build" /r /d y 2>nul
        icacls "build" /grant administrators:F /t 2>nul
        rmdir /s /q "build" 2>nul
    )
)

echo.
echo PASO 3: Limpiando cache global de Gradle...
if exist "%USERPROFILE%\.gradle\caches" (
    echo Eliminando cache de Gradle...
    rmdir /s /q "%USERPROFILE%\.gradle\caches" 2>nul
)

if exist "%USERPROFILE%\.gradle\daemon" (
    echo Eliminando daemon de Gradle...
    rmdir /s /q "%USERPROFILE%\.gradle\daemon" 2>nul
)

echo.
echo PASO 4: Eliminando archivos temporales...
if exist "%TEMP%\gradle*" (
    echo Limpiando temp de Gradle...
    del /q /f "%TEMP%\gradle*" 2>nul
)

echo.
echo PASO 5: Verificando eliminacion...
if not exist "app\build" echo ✓ app\build eliminado
if not exist ".gradle" echo ✓ .gradle eliminado  
if not exist "build" echo ✓ build eliminado
if not exist "%USERPROFILE%\.gradle\caches" echo ✓ Cache de Gradle eliminado

echo.
echo PASO 6: Recreando gradle-wrapper.properties...
echo distributionBase=GRADLE_USER_HOME > gradle\wrapper\gradle-wrapper.properties
echo distributionPath=wrapper/dists >> gradle\wrapper\gradle-wrapper.properties
echo distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip >> gradle\wrapper\gradle-wrapper.properties
echo zipStoreBase=GRADLE_USER_HOME >> gradle\wrapper\gradle-wrapper.properties
echo zipStorePath=wrapper/dists >> gradle\wrapper\gradle-wrapper.properties

echo.
echo ========================================
echo LIMPIEZA COMPLETADA
echo ========================================
echo.
echo INSTRUCCIONES FINALES:
echo 1. CERRAR Android Studio completamente
echo 2. Esperar 30 segundos
echo 3. Abrir Android Studio
echo 4. File → Invalidate Caches and Restart
echo 5. Build → Clean Project
echo 6. Build → Rebuild Project
echo.
pause




