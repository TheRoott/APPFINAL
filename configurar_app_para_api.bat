@echo off
echo ========================================
echo   CONFIGURANDO APP PARA API REMOTA
echo ========================================

echo.
echo 1. Agregando dependencias de red a build.gradle.kts...

echo.
echo Agregando Retrofit y OkHttp...
echo // Retrofit para API calls >> app\build.gradle.kts
echo implementation("com.squareup.retrofit2:retrofit:2.9.0") >> app\build.gradle.kts
echo implementation("com.squareup.retrofit2:converter-gson:2.9.0") >> app\build.gradle.kts
echo implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") >> app\build.gradle.kts

echo.
echo 2. Creando archivos de red...

echo.
echo Creando NetworkConfig.kt...
(
echo package com.example.myapplication.network
echo.
echo object NetworkConfig {
echo     const val BASE_URL = "https://recicla-contigo-backend.onrender.com"
echo     const val API_VERSION = "/api/v1"
echo     const val FULL_BASE_URL = "$BASE_URL$API_VERSION"
echo }
) > app\src\main\java\com\example\myapplication\network\NetworkConfig.kt

echo.
echo Creando ApiService.kt...
(
echo package com.example.myapplication.network
echo.
echo import com.example.myapplication.data.EnvironmentalReport
echo import retrofit2.http.*
echo.
echo interface ApiService {
echo     @GET("/reports")
echo     suspend fun getAllReports(): List^<EnvironmentalReport^>
echo     
echo     @POST("/reports")
echo     suspend fun createReport(@Body report: EnvironmentalReport): EnvironmentalReport
echo     
echo     @GET("/reports/category/{category}")
echo     suspend fun getReportsByCategory(@Path("category") category: String): List^<EnvironmentalReport^>
echo     
echo     @GET("/reports/user/{userId}")
echo     suspend fun getReportsByUser(@Path("userId") userId: String): List^<EnvironmentalReport^>
echo }
) > app\src\main\java\com\example\myapplication\network\ApiService.kt

echo.
echo Creando RetrofitClient.kt...
(
echo package com.example.myapplication.network
echo.
echo import retrofit2.Retrofit
echo import retrofit2.converter.gson.GsonConverterFactory
echo import okhttp3.OkHttpClient
echo import okhttp3.logging.HttpLoggingInterceptor
echo.
echo object RetrofitClient {
echo     private val loggingInterceptor = HttpLoggingInterceptor().apply {
echo         level = HttpLoggingInterceptor.Level.BODY
echo     }
echo     
echo     private val okHttpClient = OkHttpClient.Builder()
echo         .addInterceptor(loggingInterceptor)
echo         .build()
echo     
echo     private val retrofit = Retrofit.Builder()
echo         .baseUrl(NetworkConfig.FULL_BASE_URL)
echo         .client(okHttpClient)
echo         .addConverterFactory(GsonConverterFactory.create())
echo         .build()
echo     
echo     val apiService: ApiService = retrofit.create(ApiService::class.java)
echo }
) > app\src\main\java\com\example\myapplication\network\RetrofitClient.kt

echo.
echo 3. Archivos de red creados exitosamente!
echo.
echo 4. IMPORTANTE: Debes actualizar ReportRepository.kt para usar la API:
echo    - Cambiar de Room a Retrofit
echo    - Usar RetrofitClient.apiService
echo    - Manejar errores de red
echo.
echo 5. También actualizar MapScreen.kt para cargar desde API
echo.
echo ========================================
echo   CONFIGURACION COMPLETADA
echo ========================================
echo.
pause

