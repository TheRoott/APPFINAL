package com.example.myapplication.presentation.screens.auth

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
import androidx.compose.foundation.Image
import androidx.compose.ui.text.input.KeyboardType
import com.example.myapplication.data.AuthRepository
import com.example.myapplication.data.UserData
import com.example.myapplication.data.UserSessionManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val sessionManager = remember { UserSessionManager(context) }
    
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var startAnimation by remember { mutableStateOf(false) }
    var showLocationDialog by remember { mutableStateOf(false) }
    val alphaAnim = remember { Animatable(0f) }
    val scaleAnim = remember { Animatable(0.5f) }
    val coroutineScope = rememberCoroutineScope()
    
    // Animación infinita del gradiente
    val infiniteTransition = rememberInfiniteTransition(label = "gradient")
    val gradientOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "gradientOffset"
    )
    
    // Launcher para permisos de ubicación
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            android.util.Log.d("Login", "Permisos de ubicación concedidos")
            onNavigateToMain()
        } else {
            android.util.Log.w("Login", "Permisos de ubicación denegados")
            showLocationDialog = true
        }
    }
    
    // Función para verificar permisos de ubicación
    fun checkLocationPermissions(): Boolean {
        val fineLocation = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarseLocation = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        return fineLocation || coarseLocation
    }

    LaunchedEffect(key1 = true) {
        startAnimation = true
        alphaAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(800)
        )
        scaleAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(1000)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0F2027),
                        Color(0xFF203A43),
                        Color(0xFF2C5364),
                        Color(0xFF1A2332),
                        Color(0xFF0F2027)
                    ),
                    start = androidx.compose.ui.geometry.Offset(
                        x = 1000f * gradientOffset,
                        y = 1000f * gradientOffset
                    ),
                    end = androidx.compose.ui.geometry.Offset(
                        x = 1000f * (1 - gradientOffset),
                        y = 1000f * (1 - gradientOffset)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .alpha(alphaAnim.value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo principal con icono de reciclaje y efecto dorado
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .scale(scaleAnim.value),
                contentAlignment = Alignment.Center
            ) {
                // Círculo con gradiente dorado
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFFFD700),
                                    Color(0xFFFFA500),
                                    Color(0xFFFF8C00)
                                )
                            ),
                            shape = CircleShape
                        )
                )
                
                // Logo de reciclaje
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "♻️",
                        fontSize = 64.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Título principal
            Text(
                text = "Recicla Contigo",
                style = MaterialTheme.typography.displaySmall,
                color = Color(0xFFFFD700),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Iconos representativos
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "🏠", fontSize = 24.sp)
                Text(text = "🌱", fontSize = 24.sp)
                Text(text = "👥", fontSize = 24.sp)
                Text(text = "🌿", fontSize = 24.sp)
                Text(text = "🏢", fontSize = 24.sp)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Formulario de login
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1A2332).copy(alpha = 0.9f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Campo de email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { 
                            email = it
                            errorMessage = null // Limpiar error al escribir
                        },
                        label = { Text("Correo electrónico", color = Color(0xFFE0E0E0)) },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null, tint = Color(0xFFFFD700))
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        isError = errorMessage != null,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFD700),
                            unfocusedBorderColor = Color(0xFF4A5568),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color(0xFFE0E0E0),
                            cursorColor = Color(0xFFFFD700)
                        )
                    )
                    
                    // Campo de contraseña
                    OutlinedTextField(
                        value = password,
                        onValueChange = { 
                            password = it
                            errorMessage = null // Limpiar error al escribir
                        },
                        label = { Text("Contraseña", color = Color(0xFFE0E0E0)) },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFFFFD700))
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                                    tint = Color(0xFFE0E0E0)
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        isError = errorMessage != null,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFD700),
                            unfocusedBorderColor = Color(0xFF4A5568),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color(0xFFE0E0E0),
                            cursorColor = Color(0xFFFFD700)
                        )
                    )
                    
                    // Mostrar mensaje de error
                    if (errorMessage != null) {
                        Text(
                            text = errorMessage!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Botón de login con gradiente dorado
                    Button(
                        onClick = {
                            try {
                                android.util.Log.d("Login", "=== INICIANDO PROCESO DE LOGIN ===")
                                android.util.Log.d("Login", "Email: $email")
                                
                                // Validaciones básicas
                                if (email.isBlank()) {
                                    errorMessage = "Por favor ingresa tu correo electrónico"
                                    return@Button
                                }
                                
                                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                    errorMessage = "Por favor ingresa un correo electrónico válido"
                                    return@Button
                                }
                                
                                if (password.isBlank()) {
                                    errorMessage = "Por favor ingresa tu contraseña"
                                    return@Button
                                }
                                
                                if (password.length < 6) {
                                    errorMessage = "La contraseña debe tener al menos 6 caracteres"
                                    return@Button
                                }
                                
                                isLoading = true
                                errorMessage = null
                                
                                // Validar credenciales con el repositorio
                                coroutineScope.launch {
                                    try {
                                        android.util.Log.d("Login", "Validando credenciales...")
                                        kotlinx.coroutines.delay(500) // Simular delay de red
                                        
                                        val userCredentials = authRepository.validateCredentials(email.trim(), password)
                                        
                                        if (userCredentials != null) {
                                            // Credenciales válidas - Crear sesión
                                            android.util.Log.d("Login", "Credenciales válidas, creando sesión...")
                                            
                                            val userData = UserData(
                                                name = userCredentials.name,
                                                email = userCredentials.email,
                                                location = userCredentials.location,
                                                ecoPoints = 20, // Puntos iniciales
                                                level = "Explorador 🌱",
                                                reportsCount = 0,
                                                joinedDate = "Octubre 2024",
                                                isLoggedIn = true
                                            )
                                            
                                            sessionManager.saveUserSession(userData)
                                            
                                            android.util.Log.d("Login", "Sesión creada, verificando permisos de ubicación...")
                                            isLoading = false
                                            
                                            // Verificar permisos de ubicación antes de continuar
                                            if (checkLocationPermissions()) {
                                                android.util.Log.d("Login", "Permisos de ubicación ya concedidos, navegando a MainScreen...")
                                                onNavigateToMain()
                                            } else {
                                                android.util.Log.d("Login", "Solicitando permisos de ubicación...")
                                                locationPermissionLauncher.launch(
                                                    arrayOf(
                                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                                        Manifest.permission.ACCESS_COARSE_LOCATION
                                                    )
                                                )
                                            }
                                        } else {
                                            // Credenciales inválidas
                                            android.util.Log.w("Login", "Credenciales inválidas")
                                            isLoading = false
                                            errorMessage = "❌ Email o contraseña incorrectos. Verifica tus datos."
                                        }
                                        
                                    } catch (e: Exception) {
                                        android.util.Log.e("Login", "Error en proceso de login: ${e.message}")
                                        e.printStackTrace()
                                        isLoading = false
                                        errorMessage = "Error al iniciar sesión. Inténtalo de nuevo."
                                    }
                                }
                            } catch (e: Exception) {
                                android.util.Log.e("Login", "ERROR CRÍTICO en onClick: ${e.message}")
                                e.printStackTrace()
                                isLoading = false
                                errorMessage = "Error inesperado. Inténtalo de nuevo."
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        enabled = email.isNotEmpty() && password.isNotEmpty() && !isLoading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFD700),
                            disabledContainerColor = Color(0xFF4A5568)
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color(0xFF0F2027)
                            )
                        } else {
                            Text(
                                text = "Iniciar Sesión",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF0F2027)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Enlace a registro
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "¿No tienes cuenta? ",
                            color = Color(0xFFE0E0E0)
                        )
                        TextButton(onClick = onNavigateToRegister) {
                            Text(
                                text = "Regístrate",
                                color = Color(0xFFFFD700),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Mensaje motivacional
            Text(
                text = "Cada acción cuenta para nuestro planeta 🌍",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFE0E0E0),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light
            )
        }
    }
    
    // Dialog para ubicación requerida
    if (showLocationDialog) {
        AlertDialog(
            onDismissRequest = { showLocationDialog = false },
            title = {
                Text(
                    text = "📍 Ubicación Requerida",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text("Para usar Recicla Contigo necesitas activar la ubicación. Esto nos permite:\n\n• Mostrar reportes ambientales cercanos\n• Registrar la ubicación de tus reportes\n• Brindarte información local relevante\n\n¿Activar la ubicación ahora?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showLocationDialog = false
                        locationPermissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Activar Ubicación")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLocationDialog = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

