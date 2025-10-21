@echo off
echo ========================================
echo DESPLEGAR EN HEROKU (MÁS SIMPLE)
echo ========================================

echo.
echo 1. Crear cuenta en Heroku.com
echo 2. Instalar Heroku CLI
echo 3. Login: heroku login
echo 4. Crear app: heroku create ecovive-peru-backend
echo 5. Agregar PostgreSQL: heroku addons:create heroku-postgresql:hobby-dev
echo 6. Configurar variables:
echo    heroku config:set JWT_SECRET=tu_jwt_secret_muy_seguro_aqui
echo    heroku config:set SPRING_PROFILES_ACTIVE=production
echo 7. Desplegar: git push heroku main
echo.
echo ========================================
echo VENTAJAS DE HEROKU:
echo ========================================
echo ✅ Detecta automáticamente proyectos Java/Maven
echo ✅ PostgreSQL incluido gratis
echo ✅ Configuración automática
echo ✅ Logs claros
echo ✅ Más estable que Railway
echo.
echo ========================================
echo ALTERNATIVA: VERCEL (Node.js)
echo ========================================
echo Si Heroku no funciona, podemos convertir
echo el backend a Node.js con Express.js
echo.
pause



