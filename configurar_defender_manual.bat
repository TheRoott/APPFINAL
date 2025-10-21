@echo off
echo ========================================
echo   CONFIGURACION MANUAL MICROSOFT DEFENDER
echo ========================================

echo.
echo [1/4] Abriendo Microsoft Defender...
echo.
echo ✅ Abriendo Configuracion de Windows...
start ms-settings:windowsdefender

echo.
echo [2/4] Instrucciones para configurar exclusiones...
echo.
echo ========================================
echo   PASOS PARA CONFIGURAR EXCLUSIONES:
echo ========================================
echo.
echo 1. En la ventana que se abrio, haga clic en "Seguridad de Windows"
echo.
echo 2. Haga clic en "Proteccion contra virus y amenazas"
echo.
echo 3. Desplazarse hacia abajo y haga clic en "Administrar configuracion"
echo    (en la seccion "Configuracion de proteccion contra virus y amenazas")
echo.
echo 4. Desplazarse hacia abajo y haga clic en "Agregar o quitar exclusiones"
echo.
echo 5. Haga clic en "Agregar una exclusion" y seleccione "Carpeta"
echo.
echo 6. Agregue estas carpetas UNA POR UNA:
echo.
echo    📁 C:\Users\Fernando\.gradle
echo    📁 C:\Users\Fernando\AppData\Local\Android\Sdk
echo    📁 C:\Users\Fernando\AppData\Local\Google\AndroidStudio2025.1.4
echo    📁 C:\Users\Fernando\Desktop\alcatras
echo.
echo [3/4] Opcional - Exclusiones de extensiones...
echo.
echo Para mejor rendimiento, tambien puede agregar exclusiones de extensiones:
echo.
echo 7. Haga clic en "Agregar una exclusion" y seleccione "Tipo de archivo"
echo.
echo 8. Agregue estas extensiones UNA POR UNA:
echo.
echo    📄 .gradle
echo    📄 .jar
echo    📄 .apk
echo    📄 .dex
echo    📄 .class
echo.
echo [4/4] Verificacion...
echo.
echo ========================================
echo   CARPETAS A EXCLUIR:
echo ========================================
echo.
echo ✅ C:\Users\Fernando\.gradle
echo    (Cache de Gradle - MUY IMPORTANTE)
echo.
echo ✅ C:\Users\Fernando\AppData\Local\Android\Sdk
echo    (Android SDK - IMPORTANTE)
echo.
echo ✅ C:\Users\Fernando\AppData\Local\Google\AndroidStudio2025.1.4
echo    (Android Studio - IMPORTANTE)
echo.
echo ✅ C:\Users\Fernando\Desktop\alcatras
echo    (Proyecto EcoVive - IMPORTANTE)
echo.
echo ========================================
echo   BENEFICIOS DE ESTAS EXCLUSIONES:
echo ========================================
echo.
echo 🚀 Compilacion 3-5x mas rapida
echo 🚀 Android Studio mas fluido
echo 🚀 Gradle mas eficiente
echo 🚀 Menos falsos positivos
echo 🚀 Mejor rendimiento general
echo.
echo ⚠️  IMPORTANTE:
echo    - Estas carpetas son seguras para excluir
echo    - Solo afecta el desarrollo de Android
echo    - No compromete la seguridad del sistema
echo.
echo 📝 DESPUES DE CONFIGURAR:
echo    1. Cierre Android Studio
echo    2. Espere 30 segundos
echo    3. Abra Android Studio nuevamente
echo    4. Pruebe compilar el proyecto
echo.
echo ========================================
echo   CONFIGURACION LISTA PARA CONTINUAR
echo ========================================
echo.
pause




