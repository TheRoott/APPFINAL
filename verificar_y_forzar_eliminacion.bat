@echo off
echo ========================================
echo VERIFICANDO Y FORZANDO ELIMINACION
echo ========================================

echo.
echo Verificando directorios...
if exist "app\build" (
    echo ❌ app\build AUN EXISTE
    echo Forzando eliminacion...
    takeown /f "app\build" /r /d y
    icacls "app\build" /grant %USERNAME%:F /t
    rmdir /s /q "app\build"
    if exist "app\build" (
        echo ❌ NO SE PUDO ELIMINAR app\build
    ) else (
        echo ✅ app\build eliminado exitosamente
    )
) else (
    echo ✅ app\build no existe
)

if exist ".gradle" (
    echo ❌ .gradle AUN EXISTE
    echo Forzando eliminacion...
    takeown /f ".gradle" /r /d y
    icacls ".gradle" /grant %USERNAME%:F /t
    rmdir /s /q ".gradle"
    if exist ".gradle" (
        echo ❌ NO SE PUDO ELIMINAR .gradle
    ) else (
        echo ✅ .gradle eliminado exitosamente
    )
) else (
    echo ✅ .gradle no existe
)

if exist "build" (
    echo ❌ build AUN EXISTE
    echo Forzando eliminacion...
    takeown /f "build" /r /d y
    icacls "build" /grant %USERNAME%:F /t
    rmdir /s /q "build"
    if exist "build" (
        echo ❌ NO SE PUDO ELIMINAR build
    ) else (
        echo ✅ build eliminado exitosamente
    )
) else (
    echo ✅ build no existe
)

echo.
echo ========================================
echo VERIFICACION COMPLETADA
echo ========================================
pause




