@echo off
echo ========================================
echo   SUBIENDO PROYECTO COMPLETO A GITHUB
echo ========================================
echo.
echo Repositorio: https://github.com/TheRoott/APPFINAL.git
echo.

echo 1. Verificando si existe repositorio Git...
if exist .git (
    echo    - Repositorio Git encontrado
) else (
    echo    - Inicializando nuevo repositorio Git...
    git init
)

echo.
echo 2. Agregando todos los archivos al staging...
git add .

echo.
echo 3. Creando commit inicial...
git commit -m "Initial commit: Recicla Contigo - App Android + Backend API"

echo.
echo 4. Configurando branch principal como 'main'...
git branch -M main

echo.
echo 5. Agregando repositorio remoto de GitHub...
git remote remove origin 2>nul
git remote add origin https://github.com/TheRoott/APPFINAL.git

echo.
echo 6. Subiendo codigo a GitHub...
echo    (Si te pide credenciales, usa tu usuario y token de GitHub)
git push -u origin main

echo.
echo ========================================
echo   PROYECTO SUBIDO EXITOSAMENTE
echo ========================================
echo.
echo Tu proyecto esta ahora en:
echo https://github.com/TheRoott/APPFINAL
echo.
echo Puedes:
echo 1. Ver el codigo en GitHub
echo 2. Clonar el repositorio en otros dispositivos
echo 3. Compartir el proyecto con otros
echo 4. Desplegar el backend en servicios como Render o Railway
echo.
pause

