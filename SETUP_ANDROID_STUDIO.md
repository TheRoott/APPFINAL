# 🚀 Configuración para Android Studio - EcoVive Perú

## 📋 Pasos para Ejecutar la Aplicación

### 1. **Configurar API Keys**

#### Google Maps API Key (Requerido para mapas)
1. Ve a [Google Cloud Console](https://console.cloud.google.com/)
2. Crea un nuevo proyecto o selecciona uno existente
3. Habilita la **Maps SDK for Android**
4. Ve a **Credenciales** → **Crear credenciales** → **Clave de API**
5. Copia la API Key generada
6. Abre el archivo `local.properties` en la raíz del proyecto
7. Reemplaza `your_google_maps_api_key_here` con tu API Key real:

```properties
GOOGLE_MAPS_API_KEY=AIzaSyDtu_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

### 2. **Abrir en Android Studio**

1. **Abre Android Studio**
2. **File** → **Open** → Selecciona la carpeta `alcatras`
3. Espera a que se complete la **sincronización de Gradle**
4. Si aparece algún error, haz clic en **Sync Now**

### 3. **Configurar el Dispositivo**

#### Opción A: Emulador Android
1. **Tools** → **AVD Manager**
2. **Create Virtual Device**
3. Selecciona un dispositivo (ej: Pixel 4)
4. Descarga una imagen del sistema (API 24 o superior)
5. **Finish**

#### Opción B: Dispositivo Físico
1. Habilita **Opciones de desarrollador** en tu Android
2. Activa **Depuración USB**
3. Conecta el dispositivo por USB
4. Autoriza la depuración cuando aparezca la ventana

### 4. **Ejecutar la Aplicación**

1. Selecciona tu dispositivo/emulador en la barra superior
2. Haz clic en el botón **Run** (▶️) o presiona **Shift + F10**
3. La aplicación se compilará e instalará automáticamente

## 🔧 Solución de Problemas Comunes

### Error: "Could not find method compile()"
**Solución:** Verifica que estés usando Android Studio Hedgehog o superior.

### Error: "SDK location not found"
**Solución:** 
1. Ve a **File** → **Project Structure** → **SDK Location**
2. Verifica que la ruta del Android SDK esté configurada correctamente

### Error: "Gradle sync failed"
**Solución:**
1. Ve a **File** → **Invalidate Caches and Restart**
2. Selecciona **Invalidate and Restart**
3. Espera a que se reinicie y sincronice

### Error: "Google Maps API Key not found"
**Solución:**
1. Verifica que el archivo `local.properties` existe
2. Asegúrate de que la API Key esté correctamente configurada
3. Verifica que la API Key tenga permisos para Maps SDK for Android

### Error: "Build failed - OutOfMemoryError"
**Solución:**
1. Ve a **Help** → **Edit Custom VM Options**
2. Agrega estas líneas:
```
-Xmx4096m
-XX:MaxMetaspaceSize=512m
```
3. Reinicia Android Studio

### Error: "Plugin with id 'kotlin-kapt' not found"
**Solución:** Este error es normal si no tienes todas las dependencias. La aplicación funcionará sin algunas características avanzadas.

## 📱 Funcionalidades Disponibles

### ✅ **Funcionalidades que Funcionan Sin Configuración Adicional:**
- ✅ Pantalla de splash animada
- ✅ Login y registro
- ✅ Dashboard principal
- ✅ Sistema de reportes (sin cámara)
- ✅ Pantalla de recompensas
- ✅ Perfil de usuario
- ✅ Navegación entre pantallas

### ⚠️ **Funcionalidades que Requieren Configuración:**
- ⚠️ **Mapa interactivo** (requiere Google Maps API Key)
- ⚠️ **Cámara para reportes** (requiere permisos)
- ⚠️ **Ubicación GPS** (requiere permisos)

## 🎯 **Configuración Mínima para Probar**

Si solo quieres probar la aplicación rápidamente:

1. **Abre el proyecto en Android Studio**
2. **Sincroniza Gradle** (puede tomar unos minutos)
3. **Ejecuta en emulador o dispositivo**
4. **¡Listo!** La aplicación funcionará sin el mapa

## 📞 **Si Tienes Problemas**

### Verifica que tienes:
- ✅ Android Studio Hedgehog (2023.1.1) o superior
- ✅ Android SDK API 24 o superior
- ✅ Java 11 o superior
- ✅ Conexión a internet (para descargar dependencias)

### Logs útiles:
- **Build Output**: Para errores de compilación
- **Run**: Para errores de ejecución
- **Logcat**: Para errores en tiempo de ejecución

## 🚀 **Comandos Útiles en Terminal**

Si prefieres usar la línea de comandos:

```bash
# Limpiar proyecto
.\gradlew.bat clean

# Construir proyecto
.\gradlew.bat build

# Instalar en dispositivo
.\gradlew.bat installDebug

# Ejecutar tests
.\gradlew.bat test
```

## 📋 **Checklist Final**

Antes de ejecutar, verifica:

- [ ] Android Studio abierto y sincronizado
- [ ] Dispositivo/emulador seleccionado
- [ ] Sin errores de Gradle
- [ ] API Key configurada (opcional para funcionalidad básica)
- [ ] Permisos de cámara y ubicación habilitados (opcional)

---

**🌱 ¡La aplicación EcoVive Perú está lista para ejecutarse! 🌍**

Si encuentras algún problema específico, revisa los logs de Android Studio o contacta para obtener ayuda adicional.




