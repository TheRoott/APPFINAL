@echo off
echo ========================================
echo DESPLEGAR EN HEROKU (SUPER RÁPIDO)
echo ========================================

echo.
echo VENTAJAS DE HEROKU:
echo ✅ Detecta Java/Maven automáticamente
echo ✅ PostgreSQL incluido gratis
echo ✅ Configuración automática
echo ✅ Sin problemas de healthcheck
echo ✅ Más estable que Railway
echo.
echo ========================================
echo PASOS RÁPIDOS:
echo ========================================
echo.
echo 1. Crear cuenta en heroku.com
echo 2. Instalar Heroku CLI
echo 3. Login: heroku login
echo 4. Crear app: heroku create ecovive-peru-backend
echo 5. Agregar PostgreSQL: heroku addons:create heroku-postgresql:hobby-dev
echo 6. Configurar variables:
echo    heroku config:set JWT_SECRET=ecovive_jwt_secret_2024_muy_seguro
echo    heroku config:set SPRING_PROFILES_ACTIVE=production
echo 7. Desplegar: git push heroku main
echo.
echo ========================================
echo ¿QUIERES USAR HEROKU?
echo ========================================
echo.
echo Heroku es 10x más fácil que Railway
echo para proyectos Java/Spring Boot
echo.
pause



