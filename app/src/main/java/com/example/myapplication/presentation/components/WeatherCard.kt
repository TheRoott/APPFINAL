package com.example.myapplication.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.WeatherData
import com.example.myapplication.data.WeatherService
import kotlinx.coroutines.launch

@Composable
fun WeatherCard() {
    val coroutineScope = rememberCoroutineScope()
    var weatherData by remember { mutableStateOf<WeatherData?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    
    // Cargar clima al iniciar
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Obtener clima para Ventanilla, Callao (-11.8650, -77.1094)
                val weather = WeatherService.getCurrentWeather(-11.8650, -77.1094)
                weatherData = weather
                isLoading = false
            } catch (e: Exception) {
                android.util.Log.e("WeatherCard", "Error al cargar clima: ${e.message}")
                isLoading = false
            }
        }
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Cloud,
                    contentDescription = "Clima",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (weatherData != null) "Clima en ${weatherData!!.cityName}" else "Clima en Ventanilla",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (isLoading) {
                // Indicador de carga
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            } else if (weatherData != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${weatherData!!.temperature.toInt()}°C",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = weatherData!!.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Sensación: ${weatherData!!.feelsLike.toInt()}°C",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    }

                    Text(
                        text = weatherData!!.icon,
                        fontSize = 48.sp
                    )

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Humedad: ${weatherData!!.humidity}%",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Viento: ${(weatherData!!.windSpeed * 3.6).toInt()} km/h",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                val weatherTip = when {
                    weatherData!!.description.contains("lluvia", ignoreCase = true) -> 
                        "☔ Posibles lluvias. Ten cuidado al reportar problemas ambientales en exteriores"
                    weatherData!!.description.contains("sol", ignoreCase = true) || 
                    weatherData!!.description.contains("despejado", ignoreCase = true) -> 
                        "☀️ Día soleado, perfecto para actividades al aire libre y reportar problemas ambientales"
                    weatherData!!.description.contains("nublado", ignoreCase = true) -> 
                        "☁️ Día nublado pero ideal para salir a reportar en tu comunidad"
                    else -> 
                        "💡 Buen momento para ayudar al medio ambiente en tu comunidad"
                }
                
                Text(
                    text = weatherTip,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            } else {
                // Error al cargar
                Text(
                    text = "⚠️ No se pudo cargar el clima. Verifica tu conexión a internet.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}


