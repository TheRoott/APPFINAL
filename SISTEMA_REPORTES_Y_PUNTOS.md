# 📝 Sistema de Reportes y Puntos - Recicla Contigo

## 🎯 Características Implementadas

### 1. ✅ Base de Datos Local con Room

Se ha implementado una base de datos local usando Room para almacenar todos los reportes ambientales de forma persistente.

**Archivos creados:**
- `app/src/main/java/com/example/myapplication/data/database/AppDatabase.kt` - Configuración de la base de datos
- `app/src/main/java/com/example/myapplication/data/database/ReportEntity.kt` - Entidad de reportes
- `app/src/main/java/com/example/myapplication/data/database/ReportDao.kt` - Operaciones de acceso a datos
- `app/src/main/java/com/example/myapplication/data/database/ReportRepository.kt` - Repositorio de reportes

### 2. 🏆 Sistema de Puntos Dinámico

Los puntos se asignan automáticamente según la categoría del reporte:

#### Categorías y Puntos Base:

| Categoría | Puntos Base | Rango con Foto |
|-----------|-------------|----------------|
| 🗑️ **Basura** | **20 puntos fijos** | 25 puntos |
| 💨 **Contaminación** | 25-40 aleatorios | 30-45 |
| 🌳 **Tala de Árboles** | 30-40 aleatorios | 35-45 |
| 💧 **Contaminación del Agua** | 28-40 aleatorios | 33-45 |
| 🌫️ **Contaminación del Aire** | 25-40 aleatorios | 30-45 |
| 🦋 **Vida Silvestre** | 32-40 aleatorios | 37-45 |
| ⚠️ **Otros** | 10-20 aleatorios | 15-25 |

#### Bonus Adicionales:
- **📸 Foto adjunta**: +5 puntos
- **Total máximo por reporte**: 45 puntos

### 3. 🗺️ Visualización en el Mapa

Los reportes guardados aparecen automáticamente en el mapa según su categoría:
- Los reportes se filtran por categoría
- Muestra ubicación exacta con marcador
- Combina reportes guardados con reportes de demostración

### 4. 💾 Persistencia de Datos

Todos los reportes se guardan permanentemente en la base de datos local del dispositivo:
- **Almacenamiento**: SQLite con Room
- **Sincronización**: Automática con el mapa
- **Ubicación**: Base de datos interna de la app
- **Duración**: Los datos persisten incluso después de cerrar la app

### 5. ✨ Funcionalidades del Sistema

#### Al crear un reporte:
1. El usuario llena el formulario (título, descripción, categoría, foto opcional)
2. Presiona "Enviar Reporte"
3. El sistema:
   - Valida los campos
   - Calcula los puntos según la categoría y si tiene foto
   - Guarda el reporte en la base de datos
   - Actualiza los puntos del usuario
   - Muestra un diálogo con los puntos ganados
   - Limpia el formulario

#### En el mapa:
1. Los reportes guardados aparecen automáticamente
2. Se pueden filtrar por categoría
3. Muestran información detallada al seleccionarlos
4. Incluyen ubicación, título, descripción y estado

## 📊 Flujo de Datos

```
Usuario crea reporte
    ↓
Validación de campos
    ↓
Mapeo de categoría
    ↓
Cálculo de puntos
    ↓
Guardado en Room DB
    ↓
Actualización de puntos del usuario
    ↓
Visualización en mapa
    ↓
Dialog de confirmación
```

## 🔧 Configuración Técnica

### Dependencias Agregadas:

```kotlin
// Room Database
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
kapt("androidx.room:room-compiler:2.6.1")

// Lifecycle Compose
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
```

### Plugin Habilitado:

```kotlin
id("org.jetbrains.kotlin.kapt")
```

## 📍 Ubicación de la Base de Datos

La base de datos se almacena en:
```
/data/data/com.ecovive.peru/databases/recicla_contigo_database
```

## 🎨 UI/UX Mejorado

### Dialog de Éxito Mejorado:
- ✅ Icono de confirmación grande
- 🎉 Muestra puntos ganados destacados
- 💡 Explica el desglose de puntos
- 📸 Indica bonus por foto si aplica
- 🌿 Mensaje motivacional

### Formulario de Reporte:
- Validación de campos obligatorios
- Botón deshabilitado si faltan campos
- Indicador de carga durante el guardado
- Limpieza automática después del éxito

## 📱 Casos de Uso

### Ejemplo 1: Reporte de Basura con Foto
```
Categoría: Basura
Foto: Sí
Puntos: 20 (base) + 5 (foto) = 25 puntos
```

### Ejemplo 2: Reporte de Contaminación sin Foto
```
Categoría: Contaminación
Foto: No
Puntos: 25-40 aleatorios
```

### Ejemplo 3: Reporte de Tala de Árboles con Foto
```
Categoría: Tala de Árboles
Foto: Sí
Puntos: 30-40 (base) + 5 (foto) = 35-45 puntos
```

## 🔍 Verificación de Funcionamiento

Para verificar que el sistema funciona correctamente:

1. **Crear un reporte**:
   - Ve a la pestaña "Reportar"
   - Llena todos los campos
   - Selecciona una categoría
   - Toma o selecciona una foto (opcional)
   - Presiona "Enviar Reporte"
   - Verifica el dialog con puntos ganados

2. **Ver en el mapa**:
   - Ve a la pestaña "Mapa"
   - Filtra por la categoría del reporte creado
   - Verifica que aparece en el mapa
   - Toca el marcador para ver detalles

3. **Verificar puntos**:
   - Ve a la pestaña "Perfil"
   - Verifica que los EcoPuntos se actualizaron
   - Verifica que el contador de reportes aumentó

## 🎯 Próximas Mejoras Posibles

- [ ] Sincronización con backend
- [ ] Notificaciones de estado de reportes
- [ ] Gamificación avanzada (niveles, badges)
- [ ] Exportar reportes a PDF
- [ ] Compartir reportes en redes sociales
- [ ] Rankings de usuarios más activos
- [ ] Historial detallado de reportes

## 📝 Notas Importantes

1. **Puntos de Basura**: Siempre son exactamente 20 puntos (más bonus de foto si aplica)
2. **Otras Categorías**: Los puntos son aleatorios dentro del rango especificado
3. **Persistencia**: Los reportes NO se pierden al cerrar la app
4. **Ubicación**: Si el GPS no está disponible, usa ubicación por defecto de Ventanilla
5. **Fotos**: Las fotos se guardan como URI en la base de datos

## ⚡ Rendimiento

- Base de datos optimizada con índices
- Consultas asíncronas con Flow
- UI reactiva con StateFlow
- Sin bloqueo del hilo principal
- Carga incremental de reportes en el mapa

---

**Desarrollado con ❤️ para Recicla Contigo - Ventanilla, Callao, Perú**


