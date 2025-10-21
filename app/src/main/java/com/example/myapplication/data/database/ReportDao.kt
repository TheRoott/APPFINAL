package com.example.myapplication.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {
    
    @Query("SELECT * FROM reports ORDER BY timestamp DESC")
    fun getAllReports(): Flow<List<ReportEntity>>
    
    @Query("SELECT * FROM reports WHERE reportId = :reportId")
    suspend fun getReportById(reportId: String): ReportEntity?
    
    @Query("SELECT * FROM reports WHERE category = :category ORDER BY timestamp DESC")
    fun getReportsByCategory(category: String): Flow<List<ReportEntity>>
    
    @Query("SELECT * FROM reports WHERE userId = :userId ORDER BY timestamp DESC")
    fun getReportsByUser(userId: String): Flow<List<ReportEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: ReportEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReports(reports: List<ReportEntity>)
    
    @Update
    suspend fun updateReport(report: ReportEntity)
    
    @Delete
    suspend fun deleteReport(report: ReportEntity)
    
    @Query("DELETE FROM reports")
    suspend fun deleteAllReports()
    
    @Query("SELECT COUNT(*) FROM reports")
    suspend fun getReportCount(): Int
    
    @Query("SELECT COUNT(*) FROM reports WHERE userId = :userId")
    suspend fun getUserReportCount(userId: String): Int
    
    @Query("SELECT SUM(ecoPoints) FROM reports WHERE userId = :userId")
    suspend fun getUserTotalPoints(userId: String): Int?
}


