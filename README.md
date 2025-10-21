# 🌱 EcoVive Perú

Una aplicación móvil ambientalista profesional diseñada para fomentar la conciencia ambiental, reportar puntos críticos y recompensar acciones ecológicas en Lima, Perú (especialmente Ventanilla).

## 🎯 Características Principales

### 🔐 Autenticación
- **Login/Registro** con validación completa
- **Ubicación automática** mediante GPS
- **Animaciones fluidas** de transición

### 🏠 Pantalla Principal
- **Dashboard personalizado** con puntos ecológicos
- **Estadísticas en tiempo real** (reportes, EcoPuntos, nivel)
- **Acciones rápidas** para reportar y navegar
- **Noticias ambientales** locales
- **Consejo del día** para educar a los usuarios

### 📸 Sistema de Reportes
- **Categorías específicas**: Basura, Contaminación, Tala Ilegal, Contaminación del Agua, Maltrato Animal, Sustancias Químicas
- **Cámara integrada** para tomar fotos en tiempo real
- **Ubicación GPS** automática
- **Descripción detallada** del problema
- **Recompensas por reporte** (50 EcoPuntos por reporte válido)

### 🗺️ Mapa Interactivo
- **Visualización en tiempo real** de todos los reportes
- **Filtros por categoría** de problemas
- **Lista de reportes recientes** con detalles
- **Integración con Google Maps** (configuración requerida)

### 🏆 Sistema de Gamificación
- **EcoPuntos** acumulables por acciones ecológicas
- **Niveles de usuario**: Explorador 🌱, Protector 🌳, Guardián 🌎
- **Logros desbloqueables** con animaciones
- **Ranking mensual** por región
- **Recompensas canjeables** (plantas, kits ecológicos, etc.)

### 👤 Perfil de Usuario
- **Estadísticas personales** completas
- **Logros destacados** y pendientes
- **Configuración de la cuenta**
- **Historial de actividades**

## 🎨 Diseño y UX

### Paleta de Colores
- **Verde Ecológico**: `#2E7D32` (principal), `#4CAF50` (claro), `#1B5E20` (oscuro)
- **Azul Océano**: `#1976D2` (principal), `#42A5F5` (claro), `#0D47A1` (oscuro)
- **Colores de soporte**: Blanco natural, Marrón tierra, Azul cielo, Verde hoja

### Tipografía
- **Fuente**: Roboto (sistema)
- **Pesos**: Normal, Medium, SemiBold, Bold
- **Jerarquía clara** para mejor legibilidad

### Animaciones
- **Transiciones suaves** entre pantallas
- **Animaciones de carga** y estados
- **Efectos de partículas** sutiles
- **Logo animado** en splash screen

## 🛠️ Tecnologías Utilizadas

### Frontend
- **Jetpack Compose** - UI moderna y declarativa
- **Material Design 3** - Componentes y tema personalizado
- **Navigation Compose** - Navegación fluida entre pantallas
- **Hilt** - Inyección de dependencias

### Funcionalidades
- **Google Maps** - Mapas interactivos y ubicación
- **CameraX** - Captura de fotos optimizada
- **Retrofit** - Comunicación con APIs
- **Coil** - Carga de imágenes
- **Lottie** - Animaciones complejas

### Arquitectura
- **MVVM** (Model-View-ViewModel)
- **Clean Architecture** principles
- **Repository Pattern** para manejo de datos
- **Use Cases** para lógica de negocio

## 📱 Pantallas Implementadas

1. **Splash Screen** - Pantalla de bienvenida animada
2. **Login/Register** - Autenticación con validación
3. **Home** - Dashboard principal con estadísticas
4. **Report** - Formulario de reporte con cámara
5. **Map** - Visualización de reportes en mapa
6. **Rewards** - Sistema de logros y recompensas
7. **Profile** - Perfil de usuario y configuración

## 🚀 Instalación y Configuración

### Prerrequisitos
- Android Studio Hedgehog o superior
- JDK 11 o superior
- Android SDK API 24+
- Google Maps API Key

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/ecovive-peru.git
cd ecovive-peru
```

2. **Configurar API Keys**
```bash
cp local.properties.example local.properties
```
Editar `local.properties` y agregar tu Google Maps API Key:
```properties
GOOGLE_MAPS_API_KEY=tu_api_key_aqui
```

3. **Sincronizar proyecto**
- Abrir Android Studio
- Abrir el proyecto
- Sincronizar con Gradle Files

4. **Ejecutar la aplicación**
- Conectar dispositivo Android o iniciar emulador
- Ejecutar la aplicación

### Configuración de Google Maps

1. Ir a [Google Cloud Console](https://console.cloud.google.com/)
2. Crear un nuevo proyecto o seleccionar uno existente
3. Habilitar la API de Maps SDK para Android
4. Crear credenciales (API Key)
5. Configurar restricciones de la API Key
6. Agregar la API Key en `local.properties`

## 🎯 Funcionalidades Futuras

### Próximas Versiones
- [ ] **Notificaciones Push** inteligentes
- [ ] **Chat de soporte** con IA asistente
- [ ] **Integración con redes sociales**
- [ ] **Eventos ambientales** en tiempo real
- [ ] **Panel de administración** web
- [ ] **API backend** completa
- [ ] **Base de datos en la nube**
- [ ] **Modo offline** para reportes

### Integraciones Planificadas
- [ ] **Firebase** para backend
- [ ] **ONGs locales** para campañas
- [ ] **Municipalidades** para reportes oficiales
- [ ] **Sistema de voluntariado**

## 🌍 Impacto Ambiental

### Objetivos
- **Conciencia ambiental** en la comunidad de Ventanilla y Lima
- **Reportes rápidos** de problemas ambientales
- **Gamificación** para motivar participación
- **Educación ambiental** a través de consejos y noticias

### Métricas de Éxito
- Número de reportes enviados
- Usuarios activos mensuales
- Problemas ambientales resueltos
- Puntos de reciclaje identificados

## 👥 Contribución

### Cómo Contribuir
1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

### Estándares de Código
- Seguir las convenciones de Kotlin
- Documentar funciones públicas
- Escribir tests unitarios
- Usar nombres descriptivos para variables y funciones

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 📞 Contacto

- **Desarrollador**: [Tu Nombre]
- **Email**: tu-email@ejemplo.com
- **LinkedIn**: [Tu LinkedIn]
- **GitHub**: [Tu GitHub]

## 🙏 Agradecimientos

- Comunidad de Ventanilla, Callao
- Organizaciones ambientales de Lima
- Contribuidores de código abierto
- Material Design Team de Google

---

**🌱 ¡Juntos podemos hacer la diferencia para nuestro planeta! 🌍**




