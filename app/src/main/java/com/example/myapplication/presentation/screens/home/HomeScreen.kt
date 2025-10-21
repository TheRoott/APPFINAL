package com.example.myapplication.presentation.screens.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.UserData
import com.example.myapplication.data.UserSessionManager
import com.example.myapplication.presentation.components.EcoTipsFloatingWindow
import com.example.myapplication.presentation.components.WeatherCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController? = null,
    onNavigateToTab: (Int) -> Unit = {}
) {
    val context = LocalContext.current
    val sessionManager = remember { UserSessionManager(context) }
    var userData by remember { mutableStateOf(sessionManager.getUserSession()) }
    
    // Actualizar datos del usuario al iniciar
    LaunchedEffect(Unit) {
        userData = sessionManager.getUserSession()
    }

    val animatePoints by animateFloatAsState(
        targetValue = userData.ecoPoints.toFloat(),
        animationSpec = tween(1000),
        label = "points"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header con saludo y puntos
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Hola, ${userData.name}! 👋",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "Tienes ${animatePoints.toInt()} EcoPuntos",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "Nivel: ${userData.level}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🌱",
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Pronóstico del tiempo
        WeatherCard()

        // Acciones rápidas mejoradas
        Text(
            text = "Acciones Rápidas",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(getQuickActionsData(navController, onNavigateToTab)) { action ->
                QuickActionCard(action = action)
            }
        }

        // Estadísticas rápidas
        Text(
            text = "Tu Impacto Ambiental",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(getQuickStatsData(userData.reportsCount, userData.level)) { stat ->
                QuickStatCard(stat = stat)
            }
        }

        // Noticias ambientales
        Text(
            text = "Noticias Ambientales",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(getEnvironmentalNewsData()) { news ->
                NewsCard(news = news)
            }
        }

        // Anuncios ambientales
        EnvironmentalAdsSection()

        // Consejo del día
        EnvironmentalTipsCard()

        // Eventos próximos
        UpcomingEventsCard()

        // Consejos de sostenibilidad
        SustainabilityTipsCard()
    }
    
    // Ventana flotante con tips de eco de Lima
    EcoTipsFloatingWindow()
}

@Composable
fun WeatherForecastCard() {
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
                    text = "Clima en Ventanilla",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "22°C",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "Parcialmente nublado",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Text(
                    text = "☁️",
                    fontSize = 48.sp
                )

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Humedad: 65%",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "Viento: 12 km/h",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "💡 Día perfecto para actividades al aire libre y reportar problemas ambientales",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
        }
    }
}

@Composable
fun QuickActionCard(action: QuickAction) {
    Card(
        modifier = Modifier.width(140.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = { action.onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = action.icon,
                contentDescription = action.title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = action.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            if (action.subtitle != null) {
                Text(
                    text = action.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun QuickStatCard(stat: QuickStat) {
    Card(
        modifier = Modifier.width(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = stat.color
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stat.icon,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stat.value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = stat.label,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun NewsCard(news: EnvironmentalNews) {
    Card(
        modifier = Modifier.width(280.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = news.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = news.source,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = news.summary,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = { /* Abrir noticia */ }) {
                Text("Leer más")
            }
        }
    }
}

@Composable
fun EnvironmentalAdsSection() {
    var currentAdIndex by remember { mutableStateOf(0) }
    val ads = getEnvironmentalAdsData()

    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(5000) // Cambiar cada 5 segundos
            currentAdIndex = (currentAdIndex + 1) % ads.size
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Campaign,
                    contentDescription = "Anuncio",
                    tint = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Iniciativa Ambiental",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            val currentAd = ads[currentAdIndex]
            Text(
                text = currentAd.message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🌱 ${currentAd.organization}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                
                Button(
                    onClick = { /* Acción del anuncio */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(currentAd.actionButton)
                }
            }
        }
    }
}

@Composable
fun EnvironmentalTipsCard() {
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
                Text(
                    text = "💡",
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Consejo del Día",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Reduce tu consumo de plástico usando botellas reutilizables. Cada botella de plástico que evites usar puede tardar hasta 450 años en descomponerse.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun UpcomingEventsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = "Eventos",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Próximos Eventos",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                getUpcomingEventsData().forEach { event ->
                    EventItem(event = event)
                }
            }
        }
    }
}

@Composable
fun SustainabilityTipsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Eco,
                    contentDescription = "Sostenibilidad",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Tips de Sostenibilidad",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(getSustainabilityTips()) { tip ->
                    TipCard(tip = tip)
                }
            }
        }
    }
}

@Composable
fun EventItem(event: UpcomingEvent) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = event.icon,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${event.date} - ${event.location}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = event.points,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun TipCard(tip: SustainabilityTip) {
    Card(
        modifier = Modifier.width(150.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = tip.icon,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tip.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Data classes
data class QuickStat(val icon: String, val value: String, val label: String, val color: Color)
data class QuickAction(val icon: ImageVector, val title: String, val subtitle: String? = null, val onClick: () -> Unit)
data class EnvironmentalNews(val title: String, val source: String, val summary: String)
data class EnvironmentalAd(val message: String, val organization: String, val actionButton: String)
data class UpcomingEvent(val icon: String, val title: String, val date: String, val location: String, val points: String)
data class SustainabilityTip(val icon: String, val title: String)

// Helper functions
fun getQuickStatsData(reportsCount: Int, userLevel: String): List<QuickStat> = listOf(
    QuickStat("📝", "$reportsCount", "Reportes", Color(0xFF4CAF50)),
    QuickStat("🏆", userLevel, "Nivel", Color(0xFF2196F3)),
    QuickStat("✨", "1250", "EcoPuntos", Color(0xFFFF9800)),
    QuickStat("🌱", "7", "Días Activo", Color(0xFF4CAF50))
)

fun getQuickActionsData(navController: NavController?, onNavigateToTab: (Int) -> Unit): List<QuickAction> = listOf(
    QuickAction(
        icon = Icons.Default.CameraAlt,
        title = "Reportar",
        subtitle = "Problema ambiental",
        onClick = { onNavigateToTab(1) } // Pestaña de reporte
    ),
    QuickAction(
        icon = Icons.Default.Map,
        title = "Ver Mapa",
        subtitle = "Reportes cercanos",
        onClick = { onNavigateToTab(2) } // Pestaña de mapa
    ),
    QuickAction(
        icon = Icons.Default.EmojiEvents,
        title = "Recompensas",
        subtitle = "Canjear puntos",
        onClick = { onNavigateToTab(3) } // Pestaña de recompensas
    ),
    QuickAction(
        icon = Icons.Default.Event,
        title = "Eventos",
        subtitle = "Próximos eventos",
        onClick = { /* Navegar a eventos */ }
    ),
    QuickAction(
        icon = Icons.Default.Eco,
        title = "Tips Eco",
        subtitle = "Consejos verdes",
        onClick = { /* Navegar a tips */ }
    ),
    QuickAction(
        icon = Icons.Default.Share,
        title = "Compartir",
        subtitle = "Con amigos",
        onClick = { /* Compartir app */ }
    )
)

fun getEnvironmentalNewsData(): List<EnvironmentalNews> = listOf(
    EnvironmentalNews(
        "Inician campaña de limpieza en playas de Ventanilla",
        "Diario El Peruano",
        "Voluntarios y autoridades se unen para limpiar las playas de la región, recolectando toneladas de residuos."
    ),
    EnvironmentalNews(
        "Lima implementa ciclovías ecológicas",
        "Municipalidad de Lima",
        "Nuevas ciclovías conectan Miraflores, San Isidro y Barranco para reducir emisiones de CO2 en la capital."
    ),
    EnvironmentalNews(
        "Huertos urbanos crecen en Lima",
        "Bioferia de Surco",
        "Iniciativa ciudadana promueve agricultura urbana en distritos como Surco, Miraflores y San Borja."
    ),
    EnvironmentalNews(
        "Lomas de Lúcumo: nuevo pulmón verde",
        "SERNANP",
        "Área de conservación en Pachacamac protege ecosistemas únicos y promueve turismo sostenible en Lima."
    ),
    EnvironmentalNews(
        "Metro de Lima reduce contaminación",
        "AATE",
        "El sistema de transporte masivo ha reducido 15% las emisiones de gases contaminantes en el centro de Lima."
    ),
    EnvironmentalNews(
        "Río Rímac: proyecto de descontaminación",
        "SEDAPAL",
        "Programa integral para limpiar el río Rímac incluye tratamiento de aguas residuales y reforestación de riberas."
    ),
    EnvironmentalNews(
        "Pantanos de Villa: refugio de aves migratorias",
        "SERNANP",
        "Humedal en Chorrillos recibe miles de aves migratorias y es clave para la biodiversidad de Lima."
    )
)

fun getEnvironmentalAdsData(): List<EnvironmentalAd> = listOf(
    EnvironmentalAd(
        message = "Únete a nuestra campaña de reforestación en Ventanilla. ¡Planta un árbol y ayuda al planeta!",
        organization = "ONG Verde Perú",
        actionButton = "Participar"
    ),
    EnvironmentalAd(
        message = "Taller gratuito de reciclaje este sábado en el Centro Comunal de Ventanilla.",
        organization = "Municipalidad de Ventanilla",
        actionButton = "Inscribirse"
    ),
    EnvironmentalAd(
        message = "Concurso de fotografía ambiental: 'Ventanilla Verde'. ¡Envía tu mejor foto!",
        organization = "Cultura Ambiental",
        actionButton = "Participar"
    ),
    EnvironmentalAd(
        message = "Voluntariado de limpieza de playas cada domingo. ¡Ven y ayuda a mantener limpia nuestra costa!",
        organization = "Guardianes del Mar",
        actionButton = "Unirse"
    )
)

fun getUpcomingEventsData(): List<UpcomingEvent> = listOf(
    UpcomingEvent("🌱", "Taller de Compostaje", "15 Marzo", "Centro Comunal Ventanilla", "+50 pts"),
    UpcomingEvent("🌊", "Limpieza de Playas", "22 Marzo", "Playa Ventanilla, Callao", "+100 pts"),
    UpcomingEvent("🌳", "Plantación de Árboles", "29 Marzo", "Parque Pachacútec, Ventanilla", "+75 pts"),
    UpcomingEvent("♻️", "Feria de Reciclaje", "5 Abril", "Plaza Principal Ventanilla", "+25 pts"),
    UpcomingEvent("🚴", "Ciclovía Ambiental", "12 Abril", "Malecón de Miraflores", "+40 pts"),
    UpcomingEvent("🏞️", "Caminata Ecológica", "19 Abril", "Lomas de Lúcumo, Pachacamac", "+60 pts"),
    UpcomingEvent("🌿", "Mercado Orgánico", "26 Abril", "Bioferia de Surco", "+30 pts"),
    UpcomingEvent("🐦", "Observación de Aves", "3 Mayo", "Pantanos de Villa, Chorrillos", "+35 pts"),
    UpcomingEvent("🌊", "Limpieza de Ríos", "10 Mayo", "Río Rímac, Lima Centro", "+80 pts"),
    UpcomingEvent("🌱", "Huerto Comunitario", "17 Mayo", "Parque de la Reserva, Lima", "+45 pts"),
    UpcomingEvent("♻️", "Reciclaje Electrónico", "24 Mayo", "Centro de Lima", "+55 pts"),
    UpcomingEvent("🌳", "Reforestación Urbana", "31 Mayo", "Distrito de San Borja", "+65 pts")
)

fun getSustainabilityTips(): List<SustainabilityTip> = listOf(
    SustainabilityTip("💧", "Ahorra Agua"),
    SustainabilityTip("🔌", "Ahorra Energía"),
    SustainabilityTip("♻️", "Recicla"),
    SustainabilityTip("🚶", "Camina Más"),
    SustainabilityTip("🌱", "Planta Árboles")
)