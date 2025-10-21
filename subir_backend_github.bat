@echo off
echo ========================================
echo   SUBIENDO BACKEND A GITHUB
echo ========================================

echo.
echo 1. Inicializando repositorio Git...
cd backend
git init

echo.
echo 2. Agregando archivos...
git add .

echo.
echo 3. Commit inicial...
git commit -m "Initial commit: Backend API para Recicla Contigo"

echo.
echo 4. Configurando branch main...
git branch -M main

echo.
echo 5. IMPORTANTE: Debes crear un repositorio en GitHub primero:
echo    - Ve a https://github.com
echo    - Crea nuevo repositorio: "recicla-contigo-backend"
echo    - NO inicialices con README
echo    - Copia la URL del repositorio
echo.
echo 6. Agregando remote origin...
echo    (Reemplaza TU-USUARIO con tu usuario de GitHub)
set /p REPO_URL="Ingresa la URL del repositorio (ej: https://github.com/TU-USUARIO/recicla-contigo-backend.git): "
git remote add origin %REPO_URL%

echo.
echo 7. Subiendo a GitHub...
git push -u origin main

echo.
echo ========================================
echo   BACKEND SUBIDO EXITOSAMENTE
echo ========================================
echo.
echo Ahora puedes:
echo 1. Ir a https://render.com
echo 2. Crear cuenta gratuita
echo 3. Conectar el repositorio
echo 4. Desplegar el backend
echo.
pause