package com.example.myapplication.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToAuth: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = remember { Animatable(0f) }
    val scaleAnim = remember { Animatable(0.3f) }

    LaunchedEffect(key1 = true) {
        startAnimation = true
        alphaAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(1000)
        )
        scaleAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(1000)
        )
        delay(2000)
        // Por ahora navegar siempre al auth, después se puede verificar si el usuario ya está logueado
        onNavigateToAuth()
    }

    Splash(alpha = alphaAnim.value, scale = scaleAnim.value)
}

@Composable
fun Splash(
    alpha: Float,
    scale: Float
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F2027), // Azul oscuro profundo
                        Color(0xFF203A43), // Azul gris oscuro
                        Color(0xFF2C5364)  // Azul verde oscuro
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo animado con reciclaje
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale)
                    .alpha(alpha),
                contentAlignment = Alignment.Center
            ) {
                // Círculo con efecto dorado/plateado
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFFFD700), // Dorado
                                    Color(0xFFFFA500)  // Naranja dorado
                                )
                            ),
                            shape = CircleShape
                        )
                )
                
                // Icono de reciclaje
                Text(
                    text = "♻️",
                    fontSize = 80.sp,
                    modifier = Modifier.alpha(alpha)
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Título de la app con efecto dorado
            Text(
                text = "Recicla Contigo",
                style = MaterialTheme.typography.displayMedium,
                color = Color(0xFFFFD700), // Dorado
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(alpha)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Subtítulo elegante
            Text(
                text = "Juntos por un mundo más limpio",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFFE0E0E0), // Plata claro
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(alpha)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Iconos con efecto luminoso
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.alpha(alpha)
            ) {
                Text(text = "🏠", fontSize = 28.sp, modifier = Modifier.alpha(0.9f))
                Text(text = "🌱", fontSize = 28.sp, modifier = Modifier.alpha(0.9f))
                Text(text = "👥", fontSize = 28.sp, modifier = Modifier.alpha(0.9f))
                Text(text = "🌿", fontSize = 28.sp, modifier = Modifier.alpha(0.9f))
                Text(text = "🏢", fontSize = 28.sp, modifier = Modifier.alpha(0.9f))
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Indicador de carga elegante
            CircularProgressIndicator(
                modifier = Modifier
                    .size(40.dp)
                    .alpha(alpha),
                color = Color(0xFFFFD700),
                strokeWidth = 3.dp
            )
        }
    }
}

