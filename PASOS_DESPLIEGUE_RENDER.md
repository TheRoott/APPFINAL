# 🚀 Pasos para Desplegar en Render

## 📋 Preparación (5 minutos)

### 1. **Crear Cuenta en GitHub**
- Ir a https://github.com
- Crear cuenta gratuita
- Verificar email

### 2. **Crear Repositorio Backend**
- Click "New repository"
- Nombre: `recicla-contigo-backend`
- Descripción: "Backend API para Recicla Contigo"
- Público ✅
- **NO** marcar "Add README"
- Click "Create repository"

### 3. **Subir Código Backend**
```bash
# Ejecutar el script
subir_backend_github.bat
```

## 🌐 Despliegue en Render (10 minutos)

### 1. **Crear Cuenta en Render**
- Ir a https://render.com
- Click "Get Started for Free"
- Conectar con GitHub
- Autorizar acceso

### 2. **Crear Web Service**
- Click "New +" → "Web Service"
- Conectar repositorio: `recicla-contigo-backend`
- Configuración:
  ```
  Name: recicla-contigo-backend
  Environment: Java
  Build Command: mvn clean package
  Start Command: java -jar target/recicla-contigo-backend-1.0.0.jar
  ```

### 3. **Crear Base de Datos PostgreSQL**
- Click "New +" → "PostgreSQL"
- Nombre: `recicla-contigo-db`
- Plan: Free
- Click "Create Database"

### 4. **Configurar Variables de Entorno**
En el Web Service:
```
DATABASE_URL = [Render lo proporciona automáticamente]
SPRING_PROFILES_ACTIVE = production
```

## 📱 Actualizar App Android

### 1. **Ejecutar Script de Configuración**
```bash
configurar_app_para_api.bat
```

### 2. **Modificar ReportRepository.kt**
Cambiar de Room a API:

```kotlin
// Antes (Room local)
fun getAllReports(): Flow<List<EnvironmentalReport>>

// Después (API remota)
suspend fun getAllReports(): List<EnvironmentalReport> {
    return try {
        RetrofitClient.apiService.getAllReports()
    } catch (e: Exception) {
        // Fallback a datos locales
        MockReportsData.allReports
    }
}
```

## 🎯 URLs del Servicio

Una vez desplegado tendrás:

- **API Base**: `https://recicla-contigo-backend.onrender.com`
- **Health Check**: `https://recicla-contigo-backend.onrender.com/health`
- **Reportes**: `https://recicla-contigo-backend.onrender.com/api/v1/reports`
- **Estadísticas**: `https://recicla-contigo-backend.onrender.com/api/v1/stats`

## ✅ Verificar Despliegue

### 1. **Probar API**
```bash
curl https://recicla-contigo-backend.onrender.com/health
```

Respuesta esperada:
```json
{
  "status": "UP",
  "service": "Recicla Contigo API",
  "version": "1.0.0"
}
```

### 2. **Probar Endpoints**
```bash
# Obtener todos los reportes
curl https://recicla-contigo-backend.onrender.com/api/v1/reports

# Obtener reportes de basura
curl https://recicla-contigo-backend.onrender.com/api/v1/reports/category/TRASH
```

## 🔧 Solución de Problemas

### **Error: Build Failed**
- Verificar que Java 17 esté configurado
- Revisar logs en Render Dashboard

### **Error: Database Connection**
- Verificar que DATABASE_URL esté configurado
- Comprobar que PostgreSQL esté creado

### **Error: CORS**
- El backend ya tiene CORS configurado
- Verificar que origins = "*" esté activo

## 💰 Costos

- **Render Web Service**: Gratis (con limitaciones)
- **PostgreSQL**: Gratis (hasta 1GB)
- **Tráfico**: Gratis (hasta 100GB/mes)
- **Total**: $0/mes

## 🚀 Beneficios del Despliegue

### **Para Usuarios:**
- ✅ **Sincronización**: Reportes en la nube
- ✅ **Colaboración**: Otros usuarios ven tus reportes
- ✅ **Persistencia**: No se pierden datos
- ✅ **Tiempo Real**: Reportes aparecen inmediatamente

### **Para Administradores:**
- ✅ **Dashboard**: Ver todos los reportes
- ✅ **Estadísticas**: Análisis de problemas
- ✅ **Gestión**: Resolver reportes
- ✅ **Exportar**: Datos para autoridades

## 📊 Monitoreo

### **Render Dashboard:**
- Logs en tiempo real
- Métricas de rendimiento
- Estado del servicio
- Uso de recursos

### **Health Checks:**
- Endpoint `/health` para monitoreo
- Actuator endpoints habilitados
- Logs estructurados

---

**¡Listo para desplegar!** 🎉

Sigue los pasos en orden y tendrás tu API funcionando en 15 minutos.

