@echo off
echo ========================================
echo CREANDO REPOSITORIO BACKEND SEPARADO
echo ========================================

echo.
echo 1. Creando directorio para backend...
if not exist "backend-separado" mkdir "backend-separado"

echo.
echo 2. Copiando archivos del backend...
xcopy "backend\*" "backend-separado\" /E /I /Y

echo.
echo 3. Inicializando repositorio Git...
cd backend-separado
git init

echo.
echo 4. Agregando archivos...
git add .

echo.
echo 5. Commit inicial...
git commit -m "Backend EcoVive Perú - Spring Boot + PostgreSQL"

echo.
echo 6. Creando rama main...
git branch -M main

echo.
echo ========================================
echo REPOSITORIO BACKEND CREADO
echo ========================================
echo.
echo Ahora necesitas:
echo 1. Crear un nuevo repositorio en GitHub llamado "ecovive-backend-java"
echo 2. Ejecutar estos comandos:
echo.
echo    git remote add origin https://github.com/TheRoott/ecovive-backend-java.git
echo    git push -u origin main
echo.
echo 3. Conectar este nuevo repositorio en Railway
echo.
pause



