@echo off
echo ========================================
echo DESPLEGAR BACKEND EN HEROKU AUTOMÁTICO
echo ========================================

echo.
echo 1. Verificando Heroku CLI...
heroku --version
if %errorlevel% neq 0 (
    echo ERROR: Heroku CLI no está instalado
    echo Descarga desde: https://devcenter.heroku.com/articles/heroku-cli
    pause
    exit /b 1
)

echo.
echo 2. Login en Heroku...
heroku login

echo.
echo 3. Navegando al directorio backend...
cd /d "C:\Users\Fernando\Desktop\alcatras\backend"

echo.
echo 4. Creando aplicación en Heroku...
heroku create ecovive-peru-backend

echo.
echo 5. Agregando PostgreSQL...
heroku addons:create heroku-postgresql:hobby-dev -a ecovive-peru-backend

echo.
echo 6. Configurando variables de entorno...
heroku config:set JWT_SECRET=ecovive_jwt_secret_2024_muy_seguro -a ecovive-peru-backend
heroku config:set SPRING_PROFILES_ACTIVE=production -a ecovive-peru-backend

echo.
echo 7. Desplegando aplicación...
git push heroku main

echo.
echo ========================================
echo ¡BACKEND DESPLEGADO EXITOSAMENTE!
echo ========================================
echo.
echo URL de la aplicación: https://ecovive-peru-backend.herokuapp.com
echo.
echo Para ver logs: heroku logs --tail -a ecovive-peru-backend
echo Para abrir app: heroku open -a ecovive-peru-backend
echo.
pause



