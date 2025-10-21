package com.example.myapplication.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.screens.home.HomeScreen
import com.example.myapplication.presentation.screens.map.MapScreen
import com.example.myapplication.presentation.screens.profile.ProfileScreen
import com.example.myapplication.presentation.screens.report.ReportScreen
import com.example.myapplication.presentation.screens.rewards.RewardsScreen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon
) {
    object Home : BottomNavItem("home", "Inicio", Icons.Default.Home)
    object Report : BottomNavItem("report", "Reportar", Icons.Default.CameraAlt)
    object Map : BottomNavItem("map", "Mapa", Icons.Default.Map)
    object Rewards : BottomNavItem("rewards", "Recompensas", Icons.Default.EmojiEvents)
    object Profile : BottomNavItem("profile", "Perfil", Icons.Default.Person)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLogout: () -> Unit = {}
) {
    android.util.Log.d("MainScreen", "=== INICIANDO MAINScreen ===")
    
    var selectedTab by remember { mutableStateOf(0) }
    val navController = rememberNavController()
    
    android.util.Log.d("MainScreen", "MainScreen inicializado correctamente")
    
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Report,
        BottomNavItem.Map,
        BottomNavItem.Rewards,
        BottomNavItem.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (selectedTab == index) item.selectedIcon else item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(item.title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            android.util.Log.d("MainScreen", "Renderizando pantalla seleccionada: $selectedTab")
            when (selectedTab) {
                0 -> {
                    android.util.Log.d("MainScreen", "Cargando HomeScreen...")
                    HomeScreen(
                        navController = navController,
                        onNavigateToTab = { tabIndex -> selectedTab = tabIndex }
                    )
                }
                1 -> {
                    android.util.Log.d("MainScreen", "Cargando ReportScreen...")
                    ReportScreen()
                }
                2 -> {
                    android.util.Log.d("MainScreen", "Cargando MapScreen...")
                    MapScreen()
                }
                3 -> {
                    android.util.Log.d("MainScreen", "Cargando RewardsScreen...")
                    RewardsScreen()
                }
                4 -> {
                    android.util.Log.d("MainScreen", "Cargando ProfileScreen...")
                    ProfileScreen(onLogout = onLogout)
                }
            }
            android.util.Log.d("MainScreen", "Pantalla renderizada exitosamente")
        }
    }
}
