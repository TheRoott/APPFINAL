# EcoVive Perú - Backend

Backend para la aplicación EcoVive Perú - Sistema de reportes ambientales.

## Tecnologías

- **Java 17**
- **Spring Boot 3.2.0**
- **PostgreSQL** con PostGIS
- **JWT Authentication**
- **AWS S3** (opcional)
- **Spring Security**

## API Endpoints

### Autenticación
- `POST /api/auth/register` - Registro de usuario
- `POST /api/auth/login` - Inicio de sesión
- `POST /api/auth/refresh` - Renovar token

### Reportes
- `GET /api/reports` - Listar reportes
- `POST /api/reports` - Crear reporte
- `GET /api/reports/{id}` - Obtener reporte
- `PUT /api/reports/{id}` - Actualizar reporte
- `DELETE /api/reports/{id}` - Eliminar reporte

### Usuarios
- `GET /api/users/profile` - Perfil de usuario
- `PUT /api/users/profile` - Actualizar perfil
- `GET /api/users/achievements` - Logros del usuario

## Despliegue

Este backend está configurado para desplegarse en Railway con PostgreSQL.

### Variables de entorno necesarias:
- `DATABASE_URL` - URL de conexión a PostgreSQL
- `JWT_SECRET` - Secreto para JWT
- `SPRING_PROFILES_ACTIVE=production`

## Base de datos

El backend incluye scripts SQL para configurar la base de datos con PostGIS para funcionalidades geoespaciales.



