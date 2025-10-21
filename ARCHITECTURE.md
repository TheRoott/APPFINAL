# 🏗️ Arquitectura de EcoVive Perú

## 📱 Estructura de la Aplicación

```
EcoVive Perú (Android)
├── 🎨 Presentation Layer
│   ├── Splash Screen (Pantalla de bienvenida)
│   ├── Authentication (Login/Registro)
│   ├── Main Navigation (5 pantallas principales)
│   ├── Home Dashboard (Panel principal)
│   ├── Report System (Sistema de reportes)
│   ├── Maps Integration (Mapa interactivo)
│   ├── Rewards & Gamification (Recompensas)
│   └── User Profile (Perfil de usuario)
├── 🧭 Navigation System
│   ├── EcoViveNavigation (Navegación principal)
│   ├── Screen Definitions (Definiciones de pantallas)
│   └── Bottom Navigation (Navegación inferior)
├── 🎨 UI/UX Layer
│   ├── EcoViveTheme (Tema personalizado)
│   ├── Color Palette (Paleta de colores)
│   ├── Typography (Tipografía profesional)
│   └── Components (Componentes reutilizables)
├── 📊 Data Layer (Futuro)
│   ├── Repository Pattern
│   ├── Local Database (Room)
│   ├── Remote API (Retrofit)
│   └── Data Models
└── 🔧 Infrastructure
    ├── Dependency Injection (Hilt)
    ├── Permissions Management
    ├── Camera Integration (CameraX)
    └── Location Services (GPS)
```

## 🎯 Patrón de Arquitectura

### MVVM (Model-View-ViewModel)
- **Model**: Datos y lógica de negocio
- **View**: Interfaz de usuario (Compose)
- **ViewModel**: Estado de la UI y lógica de presentación

### Clean Architecture Principles
- **Separación de responsabilidades**
- **Inversión de dependencias**
- **Testabilidad**
- **Mantenibilidad**

## 🧩 Componentes Principales

### 1. Splash Screen
- **Propósito**: Pantalla de bienvenida con animaciones
- **Funcionalidad**: Verificación de sesión de usuario
- **Navegación**: → Login o → Main

### 2. Authentication System
- **Login Screen**: Autenticación de usuarios existentes
- **Register Screen**: Registro de nuevos usuarios
- **Validación**: Email, contraseña, confirmación
- **Ubicación**: Detección automática de GPS

### 3. Main Navigation
- **Bottom Navigation**: 5 pantallas principales
- **Navegación fluida**: Transiciones animadas
- **Estado persistente**: Mantiene selección de pestaña

### 4. Home Dashboard
- **EcoPuntos**: Sistema de puntos ecológicos
- **Estadísticas**: Reportes, nivel de usuario
- **Acciones rápidas**: Acceso directo a funciones
- **Noticias**: Contenido ambiental local
- **Consejos**: Educación ambiental diaria

### 5. Report System
- **Categorías**: 6 tipos de problemas ambientales
- **Cámara**: Captura de fotos en tiempo real
- **Ubicación**: GPS automático
- **Validación**: Campos obligatorios
- **Recompensas**: 50 EcoPuntos por reporte

### 6. Maps Integration
- **Visualización**: Reportes en tiempo real
- **Filtros**: Por categoría de problema
- **Lista**: Reportes recientes
- **Interactividad**: Marcadores clickeables

### 7. Rewards & Gamification
- **Niveles**: Explorador, Protector, Guardián
- **Logros**: Sistema de badges y medallas
- **Ranking**: Competencia mensual
- **Recompensas**: Plantas, kits ecológicos

### 8. User Profile
- **Información**: Datos personales y estadísticas
- **Logros**: Historial de conquistas
- **Configuración**: Preferencias de la app
- **Ayuda**: Soporte y documentación

## 🎨 Design System

### Color Palette
```
Verde Ecológico:
- Primary: #2E7D32
- Light: #4CAF50
- Dark: #1B5E20
- Accent: #66BB6A

Azul Océano:
- Primary: #1976D2
- Light: #42A5F5
- Dark: #0D47A1
- Accent: #64B5F6

Colores de Soporte:
- Nature White: #FAFAFA
- Earth Brown: #8D6E63
- Sky Blue: #E3F2FD
- Leaf Green: #E8F5E8
```

### Typography Scale
```
Display Large: 57sp (Bold)
Display Medium: 45sp (Bold)
Display Small: 36sp (Bold)
Headline Large: 32sp (Bold)
Headline Medium: 28sp (Bold)
Headline Small: 24sp (Bold)
Title Large: 22sp (Bold)
Title Medium: 16sp (SemiBold)
Title Small: 14sp (Medium)
Body Large: 16sp (Normal)
Body Medium: 14sp (Normal)
Body Small: 12sp (Normal)
```

## 🔄 Flujo de Datos

### 1. Autenticación
```
User Input → Validation → API Call → Success/Error → Navigation
```

### 2. Reporte Ambiental
```
Photo + Location + Description → Validation → API Upload → Points Award → UI Update
```

### 3. Gamificación
```
User Action → Points Calculation → Level Check → Achievement Check → UI Update
```

## 📱 Navegación

### Navigation Graph
```
Splash
├── Login
│   ├── Register
│   └── Main (si autenticado)
└── Main
    ├── Home
    ├── Report
    ├── Map
    ├── Rewards
    └── Profile
```

### Navigation Types
- **Stack Navigation**: Entre pantallas principales
- **Bottom Navigation**: Entre secciones del Main
- **Modal Navigation**: Para formularios y diálogos

## 🛠️ Tecnologías y Librerías

### UI Framework
- **Jetpack Compose**: UI moderna y declarativa
- **Material Design 3**: Componentes y tema
- **Navigation Compose**: Navegación fluida

### Dependency Injection
- **Hilt**: Inyección de dependencias de Google

### Maps & Location
- **Google Maps SDK**: Mapas interactivos
- **Location Services**: GPS y ubicación

### Camera & Media
- **CameraX**: Captura de fotos optimizada
- **Coil**: Carga y caché de imágenes

### Networking (Futuro)
- **Retrofit**: Cliente HTTP
- **OkHttp**: Interceptor de red
- **Gson**: Serialización JSON

### Animations
- **Lottie**: Animaciones complejas
- **Compose Animations**: Animaciones nativas

## 🔐 Seguridad

### Permisos Requeridos
- **CAMERA**: Para tomar fotos de reportes
- **ACCESS_FINE_LOCATION**: Para ubicación GPS
- **ACCESS_COARSE_LOCATION**: Para ubicación aproximada
- **READ_EXTERNAL_STORAGE**: Para acceso a galería
- **INTERNET**: Para comunicación con APIs

### Validaciones
- **Email**: Formato válido
- **Password**: Longitud mínima y complejidad
- **Location**: Verificación de permisos
- **Camera**: Verificación de disponibilidad

## 📊 Estado de la Aplicación

### State Management
- **Compose State**: Estado local de componentes
- **ViewModel**: Estado de pantallas
- **Repository**: Estado de datos (futuro)

### Data Flow
```
UI Events → ViewModel → Use Cases → Repository → Data Sources
                ↓
UI State ← ViewModel ← Use Cases ← Repository ← Data Sources
```

## 🧪 Testing Strategy

### Unit Tests
- **ViewModel Logic**: Lógica de presentación
- **Use Cases**: Lógica de negocio
- **Repository**: Manejo de datos

### Integration Tests
- **Navigation**: Flujo entre pantallas
- **API Integration**: Comunicación con backend
- **Database**: Operaciones de datos

### UI Tests
- **Compose Testing**: Interfaz de usuario
- **User Flows**: Flujos completos de usuario

## 🚀 Deployment

### Build Variants
- **Debug**: Para desarrollo y testing
- **Release**: Para producción
- **Staging**: Para pruebas de integración

### Configuration
- **API Keys**: Configuración segura
- **Environment**: URLs y configuraciones
- **ProGuard**: Ofuscación de código

## 📈 Escalabilidad

### Futuras Mejoras
- **Modularización**: Módulos independientes
- **Offline Support**: Funcionalidad sin conexión
- **Real-time Updates**: WebSockets para tiempo real
- **Analytics**: Métricas de uso
- **Crash Reporting**: Monitoreo de errores

### Performance
- **Image Optimization**: Compresión y caché
- **Lazy Loading**: Carga bajo demanda
- **Memory Management**: Gestión eficiente de memoria
- **Network Optimization**: Reducción de llamadas API

---

**Esta arquitectura está diseñada para ser escalable, mantenible y seguir las mejores prácticas de desarrollo Android moderno.**




