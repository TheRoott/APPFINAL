# 🚀 Guía de Despliegue en Render

## ✅ Paso 1: Crear Cuenta en Render
1. Ve a [render.com](https://render.com)
2. Crea una cuenta gratuita usando tu GitHub

## ✅ Paso 2: Conectar Repositorio
1. En Render Dashboard, haz clic en **"New +"**
2. Selecciona **"Blueprint"**
3. Conecta tu repositorio: `https://github.com/TheRoott/APPFINAL`
4. Autoriza el acceso a Render

## ✅ Paso 3: Despliegue Automático
Render detectará automáticamente el archivo `render.yaml` y creará:
- ✅ Un Web Service (Backend API)
- ✅ Una base de datos PostgreSQL (gratuita)
- ✅ Variables de entorno configuradas

## ⏱️ Tiempo de Despliegue
- Primera vez: ~10-15 minutos
- Siguientes deploys: ~5-7 minutos

## 🔗 URL de tu API
Una vez desplegado, Render te dará una URL como:
```
https://recicla-contigo-backend.onrender.com
```

## 🧪 Probar la API
```bash
# Health check
curl https://recicla-contigo-backend.onrender.com/actuator/health

# Crear reporte
curl -X POST https://recicla-contigo-backend.onrender.com/api/reports \
  -H "Content-Type: application/json" \
  -d '{
    "category": "PLASTICO",
    "description": "Prueba desde Render",
    "latitude": -11.9403,
    "longitude": -77.1400
  }'
```

## 📱 Conectar con la App Android
Edita `BuildConfig.kt` en tu app:
```kotlin
const val BASE_URL = "https://recicla-contigo-backend.onrender.com"
```

## ⚠️ Importante (Plan Gratuito)
- La primera request puede tardar ~30 segundos (cold start)
- Después de 15 minutos de inactividad, el servicio se duerme
- 750 horas gratis al mes (suficiente para desarrollo)

## 🔄 Actualizar el Backend
```bash
git add .
git commit -m "Actualización del backend"
git push origin main
```
Render desplegará automáticamente los cambios.

## 📊 Monitoreo
En Render Dashboard puedes ver:
- Logs en tiempo real
- Uso de recursos
- Historial de deploys
- Métricas de rendimiento

## 🎉 ¡Listo!
Tu backend está en la nube y listo para recibir reportes desde cualquier parte del mundo! 🌍

