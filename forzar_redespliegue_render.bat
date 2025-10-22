@echo off
echo ========================================
echo   FORZAR REDESPLIEGUE EN RENDER
echo ========================================
echo.

echo [INFO] Verificando ultimos commits...
git log --oneline -3
echo.

echo [ACCION] Creando commit vacio para forzar redespliegue...
git commit --allow-empty -m "Deploy: Forzar redespliegue en Render - %date% %time%"

echo.
echo [PUSH] Subiendo a GitHub...
git push origin main

echo.
echo ========================================
echo   REDESPLIEGUE FORZADO!
echo ========================================
echo.
echo Render detectara el cambio en 30-60 segundos
echo y comenzara un nuevo despliegue automaticamente.
echo.
echo Monitorea el progreso en:
echo https://dashboard.render.com
echo.
pause

