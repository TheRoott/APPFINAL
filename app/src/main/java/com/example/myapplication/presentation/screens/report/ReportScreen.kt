package com.example.myapplication.presentation.screens.report

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.myapplication.utils.CameraHelper
import com.example.myapplication.utils.CameraManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.File
import com.example.myapplication.data.database.ReportRepository
import com.example.myapplication.data.EnvironmentalReport
import com.example.myapplication.data.ReportCategory as ReportCategoryData
import com.example.myapplication.data.ReportStatus
import com.example.myapplication.utils.LocationData
import com.example.myapplication.data.UserSessionManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen() {
    val context = LocalContext.current
    
    // Repositorio y sesión
    val reportRepository = remember { ReportRepository(context) }
    val sessionManager = remember { UserSessionManager(context) }
    val userData by sessionManager.userData.collectAsStateWithLifecycle()
    
    // Estados
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ReportCategory?>(null) }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var currentLocation by remember { mutableStateOf<Location?>(null) }
    var isSubmitting by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showLocationPermissionDialog by remember { mutableStateOf(false) }
    var locationEnabled by remember { mutableStateOf(false) }
    var earnedPoints by remember { mutableStateOf(0) }
    
    // Camera manager
    val cameraManager = remember { CameraManager(context) }
    
    // Variable para almacenar la URI de la imagen
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    
    // Launcher para la cámara
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            android.util.Log.d("Camera", "Foto tomada exitosamente")
            // Actualizar photoUri solo cuando la foto se tome exitosamente
            imageUri?.let { uri ->
                photoUri = uri
                android.util.Log.d("Camera", "URI de foto actualizada: $uri")
            }
        } else {
            android.util.Log.w("Camera", "No se tomó la foto")
            photoUri = null
        }
    }
    
    // Permisos de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            android.util.Log.d("Camera", "Todos los permisos de cámara concedidos")
        } else {
            android.util.Log.w("Camera", "Permisos de cámara denegados")
        }
    }
    
    // Función para tomar foto
    val takePhoto: () -> Unit = {
        android.util.Log.d("Camera", "=== INICIANDO PROCESO DE TOMA DE FOTO ===")
        android.util.Log.d("Camera", "Dispositivo: POCO X7 Pro con MIUI")

        try {
            // Verificar contexto válido
            if (context == null) {
                android.util.Log.e("Camera", "Context es null, abortando")
            } else {
                // Verificar que la actividad está activa
                if (context is android.app.Activity) {
                    val activity = context as android.app.Activity
                    if (activity.isFinishing || activity.isDestroyed) {
                        android.util.Log.e("Camera", "Actividad terminada o destruida, abortando")
                    } else {
                        // Validación completa del setup de cámara
                        try {
                            CameraHelper.logCameraStatus(context)
                        } catch (e: Exception) {
                            android.util.Log.e("Camera", "Error en logCameraStatus: ${e.message}")
                        }

                        // Verificar permisos de forma segura
                        val hasPermissions = try {
                            CameraHelper.validateCameraSetup(context)
                        } catch (e: Exception) {
                            android.util.Log.e("Camera", "Error validando setup: ${e.message}")
                            false
                        }

                        if (!hasPermissions) {
                            android.util.Log.w("Camera", "Setup de cámara incompleto, solicitando permisos")
                            try {
                                cameraPermissionLauncher.launch(arrayOf(
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_MEDIA_IMAGES
                                ))
                            } catch (e: Exception) {
                                android.util.Log.e("Camera", "Error solicitando permisos: ${e.message}")
                            }
                        } else {
                            android.util.Log.d("Camera", "Setup de cámara validado correctamente")

                            // Crear archivo de imagen de forma ultra segura
                            val imageFile = try {
                                val cameraManager = CameraManager(context)
                                cameraManager.createImageFile(context)
                            } catch (e: Exception) {
                                android.util.Log.e("Camera", "Error al crear archivo: ${e.message}")
                                e.printStackTrace()
                                null
                            }

                            if (imageFile != null && imageFile.exists()) {
                                android.util.Log.d("Camera", "Archivo creado exitosamente: ${imageFile.absolutePath}")

                                // Verificar que el archivo es escribible
                                if (imageFile.canWrite()) {
                                    // Crear URI de forma ultra segura
                                    val createdUri = try {
                                        val authority = "${context.packageName}.fileprovider"
                                        android.util.Log.d("Camera", "Usando authority: $authority")
                                        FileProvider.getUriForFile(context, authority, imageFile)
                                    } catch (e: Exception) {
                                        android.util.Log.e("Camera", "Error al crear URI: ${e.message}")
                                        e.printStackTrace()
                                        null
                                    }

                                    if (createdUri != null) {
                                        android.util.Log.d("Camera", "URI creado exitosamente: $createdUri")

                                        // Verificar que el launcher está disponible y no es null
                                        if (cameraLauncher != null) {
                                            try {
                                                // Guardar la URI para usar después de tomar la foto
                                                imageUri = createdUri
                                                cameraLauncher.launch(createdUri)

                                                android.util.Log.d("Camera", "Cámara lanzada exitosamente")
                                            } catch (e: Exception) {
                                                android.util.Log.e("Camera", "Error al lanzar cámara: ${e.message}")
                                                e.printStackTrace()
                                            }
                                        } else {
                                            android.util.Log.e("Camera", "Camera launcher es null")
                                        }
                                    } else {
                                        android.util.Log.e("Camera", "No se pudo crear URI, abortando proceso")
                                    }
                                } else {
                                    android.util.Log.e("Camera", "Archivo no es escribible")
                                }
                            } else {
                                android.util.Log.e("Camera", "No se pudo crear archivo o archivo no existe, abortando proceso")
                            }
                        }

                        android.util.Log.d("Camera", "=== PROCESO COMPLETADO ===")
                    }
                }
            }

        } catch (e: Exception) {
            android.util.Log.e("Camera", "ERROR CRÍTICO en takePhoto: ${e.message}")
            e.printStackTrace()
        }
    }
    
    // Función para obtener ubicación
    LaunchedEffect(Unit) {
        try {
            val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
            
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        currentLocation = location
                        locationEnabled = true
                        android.util.Log.d("Location", "Ubicación obtenida: $location")
                    } else {
                        // Si no hay ubicación disponible, simular una para Ventanilla
                        currentLocation = Location("").apply {
                            latitude = -11.9000
                            longitude = -77.1000
                        }
                        locationEnabled = true
                        android.util.Log.d("Location", "Usando ubicación simulada de Ventanilla")
                    }
                }
            } else {
                // Si no hay permisos, simular ubicación para desarrollo
                currentLocation = Location("").apply {
                    latitude = -11.9000
                    longitude = -77.1000
                }
                locationEnabled = true
                android.util.Log.d("Location", "Sin permisos - usando ubicación simulada")
            }
        } catch (e: Exception) {
            android.util.Log.e("Location", "Error obteniendo ubicación: ${e.message}")
            // En caso de error, usar ubicación simulada
            currentLocation = Location("").apply {
                latitude = -11.9000
                longitude = -77.1000
            }
            locationEnabled = true
        }
    }
    
    // Coroutine scope
    val coroutineScope = rememberCoroutineScope()
    
    // Función para enviar reporte
    val submitReport: () -> Unit = {
        android.util.Log.d("ReportSubmit", "=== INICIANDO ENVÍO DE REPORTE ===")
        android.util.Log.d("ReportSubmit", "Título: '$title'")
        android.util.Log.d("ReportSubmit", "Descripción: '$description'")
        android.util.Log.d("ReportSubmit", "Ubicación habilitada: $locationEnabled")
        android.util.Log.d("ReportSubmit", "Ubicación actual: $currentLocation")
        android.util.Log.d("ReportSubmit", "Enviando: $isSubmitting")
        
        // Verificar que la ubicación esté activada antes de reportar
        if (!locationEnabled) {
            android.util.Log.w("ReportSubmit", "Ubicación no habilitada, mostrando dialog")
            showLocationPermissionDialog = true
        } else {
            android.util.Log.d("ReportSubmit", "Ubicación habilitada, iniciando envío...")
            isSubmitting = true
            
            // Simular envío
            coroutineScope.launch {
                try {
                    android.util.Log.d("ReportSubmit", "Simulando envío de reporte...")
                    kotlinx.coroutines.delay(2000)
                    isSubmitting = false
                    showSuccessDialog = true
                    android.util.Log.d("ReportSubmit", "Reporte enviado exitosamente")
                } catch (e: Exception) {
                    android.util.Log.e("ReportSubmit", "Error enviando reporte: ${e.message}")
                    isSubmitting = false
                }
            }
        }
    }
    

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Reportar Problema Ambiental",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Ayuda a mantener limpio tu entorno en Ventanilla",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Campo de título
        Text(
            text = "Título del reporte",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ej: Basura acumulada en el parque") },
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        // Categorías de reporte
        Text(
            text = "Selecciona el tipo de problema",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            getReportCategoriesData().forEach { category ->
                CategoryCard(
                    category = category,
                    isSelected = selectedCategory == category,
                    onClick = { selectedCategory = category }
                )
            }
        }

        // Campo de descripción
        Text(
            text = "Descripción del problema",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = { Text("Describe el problema en detalle...") },
            shape = RoundedCornerShape(12.dp),
            maxLines = 5
        )

        // Botón para tomar foto
        Text(
            text = "Foto del problema (opcional)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Button(
            onClick = takePhoto,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = Icons.Default.PhotoCamera,
                contentDescription = "Tomar foto"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tomar Foto")
        }

        // Mostrar foto si se tomó
        photoUri?.let { uri ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                AsyncImage(
                    model = uri,
                    contentDescription = "Foto del problema",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // Información de ubicación
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Ubicación",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (locationEnabled) {
                    "Ubicación activada ✅"
                } else {
                    "Ubicación no disponible ❌"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = if (locationEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
        
        // Información de estado del botón
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (!isSubmitting && title.isNotBlank() && description.isNotBlank()) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
            )
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "Estado del Envío:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Título: ${if (title.isNotBlank()) "✅" else "❌"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Descripción: ${if (description.isNotBlank()) "✅" else "❌"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Ubicación: ${if (locationEnabled) "✅" else "❌"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Botón: ${if (!isSubmitting && title.isNotBlank() && description.isNotBlank()) "✅ HABILITADO" else "❌ DESHABILITADO"}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = if (!isSubmitting && title.isNotBlank() && description.isNotBlank()) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
            }
        }

        // Botón para enviar reporte
        Button(
            onClick = {
                android.util.Log.d("ReportButton", "Botón de envío presionado")
                android.util.Log.d("ReportButton", "Título: '$title'")
                android.util.Log.d("ReportButton", "Descripción: '$description'")
                android.util.Log.d("ReportButton", "Categoría: ${selectedCategory?.name}")
                android.util.Log.d("ReportButton", "Foto: $photoUri")
                
                // Validar campos requeridos
                if (title.isNotBlank() && description.isNotBlank() && selectedCategory != null) {
                    android.util.Log.d("ReportButton", "Iniciando guardado de reporte...")
                    isSubmitting = true
                    
                    coroutineScope.launch {
                        try {
                            // Mapear la categoría del UI al modelo de datos
                            val categoryData = when(selectedCategory!!.name) {
                                "Basura" -> ReportCategoryData.TRASH
                                "Tala de Árboles" -> ReportCategoryData.DEFORESTATION
                                "Contaminación del Agua" -> ReportCategoryData.WATER_POLLUTION
                                "Contaminación del Suelo" -> ReportCategoryData.POLLUTION
                                "Contaminación Sonora" -> ReportCategoryData.AIR_POLLUTION
                                else -> ReportCategoryData.OTHER
                            }
                            
                            // Obtener ubicación actual o usar ubicación por defecto
                            val location = currentLocation ?: Location("").apply {
                                latitude = -11.9000
                                longitude = -77.1000
                            }
                            
                            // Crear el reporte
                            val report = EnvironmentalReport(
                                id = System.currentTimeMillis().toString(),
                                category = categoryData,
                                title = title,
                                description = description,
                                photoPath = photoUri?.toString(),
                                location = LocationData(
                                    latitude = location.latitude,
                                    longitude = location.longitude,
                                    address = "Ventanilla, Callao, Perú"
                                ),
                                timestamp = System.currentTimeMillis(),
                                status = ReportStatus.PENDING,
                                userId = userData?.email ?: "usuario@reciclacontigo.com",
                                ecoPoints = 0 // Se calculará en el repositorio
                            )
                            
                            // Guardar en la base de datos
                            android.util.Log.d("ReportButton", "Guardando reporte en BD...")
                            val reportId = reportRepository.saveReport(report)
                            
                            if (reportId > 0) {
                                // Obtener los puntos ganados
                                val points = reportRepository.getUserTotalPoints(userData?.email ?: "usuario@reciclacontigo.com")
                                val previousPoints = userData?.ecoPoints ?: 0
                                earnedPoints = points - previousPoints
                                
                                // Actualizar puntos del usuario
                                if (userData != null) {
                                    val updatedUser = userData!!.copy(
                                        ecoPoints = points,
                                        reportsCount = userData!!.reportsCount + 1
                                    )
                                    sessionManager.saveUserSession(updatedUser)
                                }
                                
                                isSubmitting = false
                                showSuccessDialog = true
                                android.util.Log.d("ReportButton", "Reporte guardado exitosamente con ID: $reportId, Puntos ganados: $earnedPoints")
                            } else {
                                isSubmitting = false
                                android.util.Log.e("ReportButton", "Error al guardar el reporte")
                            }
                        } catch (e: Exception) {
                            android.util.Log.e("ReportButton", "Error: ${e.message}", e)
                            isSubmitting = false
                        }
                    }
                } else {
                    android.util.Log.w("ReportButton", "Faltan campos requeridos")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isSubmitting && title.isNotBlank() && description.isNotBlank() && selectedCategory != null,
            shape = RoundedCornerShape(12.dp)
        ) {
            if (isSubmitting) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Enviando...")
            } else {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Enviar reporte"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Enviar Reporte")
            }
        }
    }

    // Dialog de éxito
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "✅",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "¡Reporte Guardado!",
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Tu reporte ha sido guardado exitosamente.",
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "🎉 Puntos Ganados",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "+$earnedPoints",
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            if (selectedCategory?.name == "Basura") {
                                Text(
                                    text = "Basura = 20 puntos base",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                            if (photoUri != null) {
                                Text(
                                    text = "+ 5 puntos por foto",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Gracias por ayudar a mantener limpio Ventanilla 🌿",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                }
            },
            confirmButton = {
                Button(onClick = { 
                    showSuccessDialog = false
                    // Limpiar formulario
                    title = ""
                    description = ""
                    photoUri = null
                    selectedCategory = null
                    earnedPoints = 0
                }) {
                    Text("Aceptar")
                }
            }
        )
    }

    // Dialog para activar ubicación
    if (showLocationPermissionDialog) {
        LocationPermissionDialog(
            onDismiss = { showLocationPermissionDialog = false },
            onLocationEnabled = {
                locationEnabled = true
                showLocationPermissionDialog = false
            }
        )
    }
}

@Composable
fun CategoryCard(
    category: ReportCategory,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category.emoji,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(end = 12.dp)
            )
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
                Text(
                    text = "${category.points} puntos",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
            
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Seleccionado",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun LocationPermissionDialog(
    onDismiss: () -> Unit,
    onLocationEnabled: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "📍 Ubicación Requerida",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Para reportar problemas ambientales, necesitamos acceder a tu ubicación.",
                    style = MaterialTheme.typography.bodyMedium
                )
                
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "🎯 Beneficios de activar la ubicación:",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "• Reportes más precisos y útiles",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "• Ubicación automática en tus reportes",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "• Mejor experiencia para otros usuarios",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "• Ayudas a localizar problemas ambientales",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onLocationEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Ubicación",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Activar Ubicación")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

// Data classes
data class ReportCategory(
    val name: String,
    val emoji: String,
    val points: Int
)

enum class ReportCategoryEnum(
    val category: ReportCategory
) {
    Basura(ReportCategory("Basura", "🗑️", 50)),
    Tala(ReportCategory("Tala de Árboles", "🌳", 100)),
    Agua(ReportCategory("Contaminación del Agua", "💧", 80)),
    Suelo(ReportCategory("Contaminación del Suelo", "🌍", 60)),
    Ruido(ReportCategory("Contaminación Sonora", "🔊", 40)),
    Otros(ReportCategory("Otros Problemas", "⚠️", 30))
}

// Helper functions
fun getReportCategoriesData(): List<ReportCategory> = ReportCategoryEnum.values().map { it.category }