# 🚀 Despliegue en Render - Recicla Contigo

## 📋 Preparación del Proyecto

### 1. **Crear Backend API (Spring Boot)**

Primero necesitamos crear un backend API para que la app móvil se conecte a un servidor real.

#### Estructura del Backend:
```
backend/
├── src/main/java/com/reciclacontigo/
│   ├── ReciclaContigoApplication.java
│   ├── controller/
│   │   └── ReportController.java
│   ├── model/
│   │   ├── Report.java
│   │   └── User.java
│   ├── repository/
│   │   └── ReportRepository.java
│   └── service/
│       └── ReportService.java
├── src/main/resources/
│   └── application.properties
├── pom.xml
└── Procfile
```

### 2. **Configuración para Render**

#### Archivos necesarios:

**Procfile:**
```
web: java -jar target/recicla-contigo-backend-1.0.0.jar
```

**pom.xml:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.reciclacontigo</groupId>
    <artifactId>recicla-contigo-backend</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <name>Recicla Contigo Backend</name>
    <description>Backend API para Recicla Contigo</description>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>
    
    <properties>
        <java.version>17</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### 3. **Configuración de la App Android**

#### Modificar para usar API remota:

**NetworkConfig.kt:**
```kotlin
package com.example.myapplication.network

object NetworkConfig {
    const val BASE_URL = "https://recicla-contigo-backend.onrender.com"
    const val API_VERSION = "/api/v1"
    const val FULL_BASE_URL = "$BASE_URL$API_VERSION"
}
```

**ApiService.kt:**
```kotlin
package com.example.myapplication.network

import retrofit2.http.*

interface ApiService {
    @GET("/reports")
    suspend fun getAllReports(): List<EnvironmentalReport>
    
    @POST("/reports")
    suspend fun createReport(@Body report: EnvironmentalReport): EnvironmentalReport
    
    @GET("/reports/category/{category}")
    suspend fun getReportsByCategory(@Path("category") category: String): List<EnvironmentalReport>
}
```

## 🌐 Pasos para Desplegar en Render

### **Paso 1: Crear Repositorio en GitHub**

1. **Crear nuevo repositorio:**
   - Nombre: `recicla-contigo-backend`
   - Descripción: "Backend API para Recicla Contigo - Ventanilla, Callao"
   - Público

2. **Subir código del backend:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit: Backend API"
   git branch -M main
   git remote add origin https://github.com/tu-usuario/recicla-contigo-backend.git
   git push -u origin main
   ```

### **Paso 2: Configurar en Render**

1. **Ir a Render.com**
2. **Crear cuenta** (gratis)
3. **Nuevo Web Service**
4. **Conectar GitHub:**
   - Seleccionar repositorio `recicla-contigo-backend`
   - Branch: `main`

5. **Configuración del servicio:**
   ```
   Name: recicla-contigo-backend
   Environment: Java
   Build Command: mvn clean package
   Start Command: java -jar target/recicla-contigo-backend-1.0.0.jar
   ```

6. **Variables de entorno:**
   ```
   SPRING_PROFILES_ACTIVE=production
   DATABASE_URL=postgresql://... (Render lo proporciona)
   ```

### **Paso 3: Base de Datos PostgreSQL**

1. **En Render Dashboard:**
   - Crear "New PostgreSQL"
   - Nombre: `recicla-contigo-db`
   - Plan: Free

2. **Configurar conexión:**
   - Render automáticamente proporciona `DATABASE_URL`
   - La app se conectará automáticamente

### **Paso 4: Actualizar App Android**

1. **Modificar URLs:**
   ```kotlin
   // Cambiar de local a remoto
   const val BASE_URL = "https://recicla-contigo-backend.onrender.com"
   ```

2. **Agregar dependencias de red:**
   ```kotlin
   // Retrofit
   implementation("com.squareup.retrofit2:retrofit:2.9.0")
   implementation("com.squareup.retrofit2:converter-gson:2.9.0")
   implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
   ```

## 🔧 Configuración Avanzada

### **CORS para App Móvil:**
```java
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ReportController {
    // Endpoints aquí
}
```

### **Variables de Entorno:**
```properties
# application.properties
spring.datasource.url=${DATABASE_URL}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
server.port=${PORT:8080}
```

## 📱 URLs del Servicio

Una vez desplegado, tendrás:

- **API Base:** `https://recicla-contigo-backend.onrender.com`
- **Health Check:** `https://recicla-contigo-backend.onrender.com/health`
- **Reportes:** `https://recicla-contigo-backend.onrender.com/api/v1/reports`

## 🎯 Beneficios del Despliegue

### ✅ **Para Usuarios:**
- **Sincronización**: Reportes se guardan en la nube
- **Colaboración**: Otros usuarios ven tus reportes
- **Persistencia**: No se pierden datos al cambiar de dispositivo
- **Tiempo Real**: Reportes aparecen inmediatamente

### ✅ **Para Administradores:**
- **Dashboard**: Ver todos los reportes de Ventanilla
- **Estadísticas**: Análisis de problemas ambientales
- **Gestión**: Resolver reportes desde web
- **Exportar**: Datos para autoridades

## 🚀 Próximos Pasos

1. **Crear backend API** (Spring Boot)
2. **Configurar base de datos** (PostgreSQL)
3. **Desplegar en Render** (gratis)
4. **Actualizar app Android** (conectar a API)
5. **Probar funcionalidad** (reportes en tiempo real)

## 💰 Costos

- **Render**: Gratis (con limitaciones)
- **PostgreSQL**: Gratis (hasta 1GB)
- **Dominio**: Opcional (gratis con .onrender.com)

## 🔒 Seguridad

- **HTTPS**: Automático en Render
- **CORS**: Configurado para app móvil
- **Validación**: Datos validados en backend
- **Rate Limiting**: Protección contra spam

---

**¿Empezamos con el backend?** 🚀

