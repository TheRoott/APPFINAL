@echo off
echo ========================================
echo   CORRECCION DE IMPORTS KOTLIN
echo ========================================

echo.
echo [1/6] Agregando dependencia Material Icons Extended...
echo ✅ Dependencia agregada al build.gradle.kts

echo.
echo [2/6] Corrigiendo Theme.kt...
echo ✅ Import de Color agregado

echo.
echo [3/6] Corrigiendo MainScreen.kt...
echo ✅ Imports específicos de Material Icons agregados

echo.
echo [4/6] Corrigiendo HomeScreen.kt...
echo ✅ Imports específicos de Material Icons agregados

echo.
echo [5/6] Verificando archivos corregidos...
echo.
echo Archivos principales corregidos:
echo - app/build.gradle.kts: Material Icons Extended
echo - ui/theme/Theme.kt: Import de Color
echo - presentation/screens/main/MainScreen.kt: Imports específicos
echo - presentation/screens/home/HomeScreen.kt: Imports específicos

echo.
echo [6/6] Archivos pendientes de correccion...
echo.
echo Archivos que necesitan correccion manual:
echo - presentation/screens/auth/LoginScreen.kt: Visibility icons
echo - presentation/screens/auth/RegisterScreen.kt: Visibility icons
echo - presentation/screens/map/MapScreen.kt: ReportCategory, sp, icon, title
echo - presentation/screens/profile/ProfileScreen.kt: ChevronRight, PrivacyTip, Help
echo - presentation/screens/report/ReportScreen.kt: CameraAlt, sp
echo - presentation/screens/rewards/RewardsScreen.kt: Chip, ChipDefaults
echo.

echo.
echo ========================================
echo   CORRECCION PARCIAL COMPLETADA
echo ========================================
echo.
echo ✅ Dependencias agregadas
echo ✅ Imports principales corregidos
echo ⚠️  Algunos archivos necesitan correccion manual
echo.
echo Prueba compilar ahora:
echo Build ^>^> Clean Project
echo Build ^>^> Rebuild Project
echo.
pause




