package com.example.myapplication.data.database

import android.content.Context
import android.util.Log
import com.example.myapplication.data.EnvironmentalReport
import com.example.myapplication.data.ReportCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.random.Random

class ReportRepository(context: Context) {
    
    private val reportDao = AppDatabase.getDatabase(context).reportDao()
    private val TAG = "ReportRepository"
    
    // Obtener todos los reportes como Flow
    fun getAllReports(): Flow<List<EnvironmentalReport>> {
        return reportDao.getAllReports().map { entities ->
            entities.map { it.toEnvironmentalReport() }
        }
    }
    
    // Obtener reportes por categoría
    fun getReportsByCategory(category: ReportCategory): Flow<List<EnvironmentalReport>> {
        return reportDao.getReportsByCategory(category.name).map { entities ->
            entities.map { it.toEnvironmentalReport() }
        }
    }
    
    // Obtener reportes por usuario
    fun getReportsByUser(userId: String): Flow<List<EnvironmentalReport>> {
        return reportDao.getReportsByUser(userId).map { entities ->
            entities.map { it.toEnvironmentalReport() }
        }
    }
    
    // Guardar un reporte
    suspend fun saveReport(report: EnvironmentalReport): Long {
        return try {
            // Calcular puntos según la categoría
            val points = calculateEcoPoints(report.category, report.photoPath != null)
            
            // Actualizar el reporte con los puntos calculados
            val updatedReport = report.copy(ecoPoints = points)
            
            val entity = updatedReport.toReportEntity()
            val id = reportDao.insertReport(entity)
            
            Log.d(TAG, "Reporte guardado: ${report.title}, Puntos: $points, ID: $id")
            id
        } catch (e: Exception) {
            Log.e(TAG, "Error guardando reporte: ${e.message}", e)
            -1
        }
    }
    
    // Calcular puntos eco según la categoría
    private fun calculateEcoPoints(category: ReportCategory, hasPhoto: Boolean): Int {
        // Puntos base según categoría
        val basePoints = when (category) {
            ReportCategory.TRASH -> 20 // Basura siempre 20 puntos
            ReportCategory.POLLUTION -> Random.nextInt(25, 41) // 25-40 puntos
            ReportCategory.DEFORESTATION -> Random.nextInt(30, 41) // 30-40 puntos
            ReportCategory.WATER_POLLUTION -> Random.nextInt(28, 41) // 28-40 puntos
            ReportCategory.AIR_POLLUTION -> Random.nextInt(25, 41) // 25-40 puntos
            ReportCategory.WILDLIFE -> Random.nextInt(32, 41) // 32-40 puntos
            ReportCategory.OTHER -> Random.nextInt(10, 21) // 10-20 puntos
        }
        
        // Bonus por foto (5 puntos adicionales)
        val photoBonus = if (hasPhoto) 5 else 0
        
        val totalPoints = basePoints + photoBonus
        
        Log.d(TAG, "Puntos calculados - Categoría: ${category.title}, Base: $basePoints, Bonus foto: $photoBonus, Total: $totalPoints")
        
        return totalPoints
    }
    
    // Actualizar un reporte
    suspend fun updateReport(report: EnvironmentalReport) {
        try {
            val entity = report.toReportEntity()
            reportDao.updateReport(entity)
            Log.d(TAG, "Reporte actualizado: ${report.title}")
        } catch (e: Exception) {
            Log.e(TAG, "Error actualizando reporte: ${e.message}", e)
        }
    }
    
    // Eliminar un reporte
    suspend fun deleteReport(report: EnvironmentalReport) {
        try {
            val entity = report.toReportEntity()
            reportDao.deleteReport(entity)
            Log.d(TAG, "Reporte eliminado: ${report.title}")
        } catch (e: Exception) {
            Log.e(TAG, "Error eliminando reporte: ${e.message}", e)
        }
    }
    
    // Obtener el conteo total de reportes
    suspend fun getReportCount(): Int {
        return try {
            reportDao.getReportCount()
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo conteo de reportes: ${e.message}", e)
            0
        }
    }
    
    // Obtener el conteo de reportes por usuario
    suspend fun getUserReportCount(userId: String): Int {
        return try {
            reportDao.getUserReportCount(userId)
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo conteo de reportes del usuario: ${e.message}", e)
            0
        }
    }
    
    // Obtener puntos totales del usuario
    suspend fun getUserTotalPoints(userId: String): Int {
        return try {
            reportDao.getUserTotalPoints(userId) ?: 0
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo puntos del usuario: ${e.message}", e)
            0
        }
    }
}


