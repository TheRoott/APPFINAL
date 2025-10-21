package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.EnvironmentalReport
import com.example.myapplication.data.ReportCategory
import com.example.myapplication.data.ReportStatus
import com.example.myapplication.utils.LocationData

@Entity(tableName = "reports")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val reportId: String,
    val category: String, // ReportCategory as String
    val title: String,
    val description: String,
    val photoPath: String? = null,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val timestamp: Long,
    val status: String, // ReportStatus as String
    val userId: String,
    val ecoPoints: Int
)

// Extension functions para convertir entre ReportEntity y EnvironmentalReport
fun ReportEntity.toEnvironmentalReport(): EnvironmentalReport {
    return EnvironmentalReport(
        id = reportId,
        category = ReportCategory.valueOf(category),
        title = title,
        description = description,
        photoPath = photoPath,
        location = LocationData(
            latitude = latitude,
            longitude = longitude,
            address = address
        ),
        timestamp = timestamp,
        status = ReportStatus.valueOf(status),
        userId = userId,
        ecoPoints = ecoPoints
    )
}

fun EnvironmentalReport.toReportEntity(): ReportEntity {
    return ReportEntity(
        reportId = id,
        category = category.name,
        title = title,
        description = description,
        photoPath = photoPath,
        latitude = location.latitude,
        longitude = location.longitude,
        address = location.address,
        timestamp = timestamp,
        status = status.name,
        userId = userId,
        ecoPoints = ecoPoints
    )
}


