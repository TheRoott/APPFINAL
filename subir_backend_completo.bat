@echo off
echo ========================================
echo SUBIENDO BACKEND COMPLETO A GITHUB
echo ========================================

echo.
echo 1. Navegando al directorio backend...
cd /d "C:\Users\Fernando\Desktop\alcatras\backend"

echo.
echo 2. Verificando archivos...
dir

echo.
echo 3. Inicializando Git...
git init

echo.
echo 4. Agregando todos los archivos...
git add .

echo.
echo 5. Creando commit...
git commit -m "Backend EcoVive Perú - Spring Boot + PostgreSQL + JWT"

echo.
echo 6. Configurando rama main...
git branch -M main

echo.
echo 7. Agregando remote origin...
git remote add origin https://github.com/TheRoott/ecovive-backend-java.git

echo.
echo 8. Subiendo a GitHub...
git push -u origin main

echo.
echo ========================================
echo VERIFICANDO RESULTADO
echo ========================================
echo.
echo Verifica en GitHub: https://github.com/TheRoott/ecovive-backend-java
echo.
pause



