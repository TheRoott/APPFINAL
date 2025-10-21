package com.example.myapplication.presentation.screens.auth

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.UserSessionManager
import com.example.myapplication.data.AuthRepository
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { UserSessionManager(context) }
    val authRepository = remember { AuthRepository(context) }
    val coroutineScope = rememberCoroutineScope()
    
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var startAnimation by remember { mutableStateOf(false) }
    var showWelcomeDialog by remember { mutableStateOf(false) }
    val alphaAnim = remember { Animatable(0f) }
    val scaleAnim = remember { Animatable(0.5f) }
    
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
                    .size(150.dp)
                    .scale(scaleAnim.value),
                contentAlignment = Alignment.Center
            ) {
                // Círculo con gradiente dorado
                Box(
                    modifier = Modifier
                        .size(120.dp)
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
                Text(
                    text = "♻️",
                    fontSize = 56.sp
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Título principal
            Text(
                text = "Recicla Contigo",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFFFFD700),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Crea tu cuenta y comienza a cuidar el planeta",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFFE0E0E0),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Formulario de registro
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
                    // Mostrar mensaje de error
                    if (errorMessage != null) {
                        Text(
                            text = errorMessage!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    
                    // Campo de nombre
                    OutlinedTextField(
                        value = name,
                        onValueChange = { 
                            name = it
                            errorMessage = null
                        },
                        label = { Text("Nombre completo", color = Color(0xFFE0E0E0)) },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFFFFD700))
                        },
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
                    
                    // Campo de email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { 
                            email = it
                            errorMessage = null
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
                        onValueChange = { password = it },
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
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFD700),
                            unfocusedBorderColor = Color(0xFF4A5568),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color(0xFFE0E0E0),
                            cursorColor = Color(0xFFFFD700)
                        )
                    )
                    
                    // Campo de confirmar contraseña
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirmar contraseña", color = Color(0xFFE0E0E0)) },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFFFFD700))
                        },
                        trailingIcon = {
                            IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                Icon(
                                    imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = if (confirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                                    tint = Color(0xFFE0E0E0)
                                )
                            }
                        },
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFD700),
                            unfocusedBorderColor = Color(0xFF4A5568),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color(0xFFE0E0E0),
                            cursorColor = Color(0xFFFFD700)
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Botón de registro
                    Button(
                        onClick = {
                            // Validaciones
                            if (name.isBlank()) {
                                errorMessage = "Por favor ingresa tu nombre completo"
                                return@Button
                            }
                            
                            if (email.isBlank()) {
                                errorMessage = "Por favor ingresa tu correo electrónico"
                                return@Button
                            }
                            
                            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                errorMessage = "Por favor ingresa un correo electrónico válido"
                                return@Button
                            }
                            
                            if (password.isBlank()) {
                                errorMessage = "Por favor ingresa una contraseña"
                                return@Button
                            }
                            
                            if (password.length < 6) {
                                errorMessage = "La contraseña debe tener al menos 6 caracteres"
                                return@Button
                            }
                            
                            if (confirmPassword.isBlank()) {
                                errorMessage = "Por favor confirma tu contraseña"
                                return@Button
                            }
                            
                            if (password != confirmPassword) {
                                errorMessage = "Las contraseñas no coinciden"
                                return@Button
                            }
                            
                            isLoading = true
                            errorMessage = null
                            
                            // Registrar usuario con AuthRepository
                            coroutineScope.launch {
                                try {
                                    // Simular delay de red
                                    kotlinx.coroutines.delay(800)
                                    
                                    // Intentar registrar el usuario
                                    val registrationSuccess = authRepository.registerUser(
                                        email = email.trim(),
                                        password = password,
                                        name = name.trim(),
                                        location = "Ventanilla, Callao"
                                    )
                                    
                                    if (registrationSuccess) {
                                        // Registro exitoso - Crear sesión de usuario con 20 puntos de bienvenida
                                        val userData = com.example.myapplication.data.UserData(
                                            name = name.trim(),
                                            email = email.trim(),
                                            location = "Ventanilla, Callao",
                                            ecoPoints = 20, // Puntos de bienvenida
                                            level = "Explorador 🌱",
                                            reportsCount = 0,
                                            joinedDate = "Octubre 2024",
                                            isLoggedIn = true
                                        )
                                        
                                        // Guardar sesión
                                        sessionManager.saveUserSession(userData)
                                        
                                        android.util.Log.d("Register", "Usuario registrado exitosamente: ${email.trim()}")
                                        
                                        isLoading = false
                                        showWelcomeDialog = true
                                    } else {
                                        // El email ya está registrado
                                        android.util.Log.w("Register", "Email ya registrado: ${email.trim()}")
                                        isLoading = false
                                        errorMessage = "❌ Este correo electrónico ya está registrado. Inicia sesión."
                                    }
                                    
                                } catch (e: Exception) {
                                    android.util.Log.e("Register", "Error en registro: ${e.message}")
                                    e.printStackTrace()
                                    isLoading = false
                                    errorMessage = "Error al crear la cuenta. Inténtalo de nuevo."
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        enabled = name.isNotEmpty() && email.isNotEmpty() && 
                                password.isNotEmpty() && confirmPassword.isNotEmpty() && 
                                password == confirmPassword && !isLoading,
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
                                text = "Crear Cuenta",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF0F2027)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Enlace a login
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "¿Ya tienes cuenta? ",
                            color = Color(0xFFE0E0E0)
                        )
                        TextButton(onClick = onNavigateToLogin) {
                            Text(
                                text = "Inicia sesión",
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
                text = "Juntos podemos hacer la diferencia 🌿",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
    
    // Dialog de bienvenida con puntos
    if (showWelcomeDialog) {
        AlertDialog(
            onDismissRequest = { 
                showWelcomeDialog = false
                onNavigateToMain()
            },
            title = {
                Text(
                    text = "🎉 ¡Bienvenido a Recicla Contigo!",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = "¡Felicitaciones $name! Tu cuenta ha sido creada exitosamente.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "🌟 Has ganado 20 puntos de bienvenida por unirte a nuestra comunidad ecológica.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Estos puntos te ayudarán a subir de nivel y desbloquear recompensas especiales mientras ayudas al medio ambiente.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showWelcomeDialog = false
                        onNavigateToMain()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("¡Comenzar!")
                }
            }
        )
    }
}

