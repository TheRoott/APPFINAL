package com.example.myapplication.data

import com.example.myapplication.utils.LocationData

data class EnvironmentalReport(
    val id: String = System.currentTimeMillis().toString(),
    val category: ReportCategory,
    val title: String,
    val description: String,
    val photoPath: String? = null,
    val location: LocationData,
    val timestamp: Long = System.currentTimeMillis(),
    val status: ReportStatus = ReportStatus.PENDING,
    val userId: String = "user_${System.currentTimeMillis()}",
    val ecoPoints: Int = 0
)

enum class ReportCategory(val title: String, val icon: String, val ecoPoints: Int) {
    TRASH("Basura", "🗑️", 10),
    POLLUTION("Contaminación", "💨", 15),
    DEFORESTATION("Tala de Árboles", "🌳", 20),
    WATER_POLLUTION("Contaminación del Agua", "💧", 25),
    AIR_POLLUTION("Contaminación del Aire", "🌫️", 20),
    WILDLIFE("Vida Silvestre", "🦋", 30),
    OTHER("Otros", "📝", 5)
}

enum class ReportStatus(val title: String) {
    PENDING("Pendiente"),
    IN_PROGRESS("En Progreso"),
    RESOLVED("Resuelto"),
    VERIFIED("Verificado")
}

data class EcoReward(
    val id: String,
    val title: String,
    val description: String,
    val ecoPoints: Int,
    val category: RewardCategory,
    val location: String? = null,
    val isAvailable: Boolean = true,
    val icon: String = "🎁"
)

enum class RewardCategory {
    LOCAL_BUSINESS,
    ECO_PRODUCT,
    EXPERIENCE,
    DISCOUNT,
    DONATION
}




