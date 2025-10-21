package com.example.myapplication.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

data class WeatherData(
    val temperature: Double,
    val feelsLike: Double,
    val description: String,
    val humidity: Int,
    val windSpeed: Double,
    val cityName: String,
    val icon: String
)

object WeatherService {
    // API Key gratuita de OpenWeatherMap - El usuario puede obtener la suya en https://openweathermap.org/api
    private const val API_KEY = "bd5e378503939ddaee76f12ad7a97608" // Demo key
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/weather"
    private const val TAG = "WeatherService"
    
    /**
     * Obtiene el clima actual para las coordenadas dadas
     */
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): WeatherData? {
        return withContext(Dispatchers.IO) {
            try {
                val url = "$BASE_URL?lat=$latitude&lon=$longitude&appid=$API_KEY&units=metric&lang=es"
                Log.d(TAG, "Solicitando clima para: lat=$latitude, lon=$longitude")
                
                val response = URL(url).readText()
                val jsonObject = JSONObject(response)
                
                val main = jsonObject.getJSONObject("main")
                val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
                val wind = jsonObject.getJSONObject("wind")
                
                val weatherData = WeatherData(
                    temperature = main.getDouble("temp"),
                    feelsLike = main.getDouble("feels_like"),
                    description = weather.getString("description").capitalize(),
                    humidity = main.getInt("humidity"),
                    windSpeed = wind.getDouble("speed"),
                    cityName = jsonObject.getString("name"),
                    icon = getWeatherEmoji(weather.getString("main"), weather.getString("icon"))
                )
                
                Log.d(TAG, "Clima obtenido: $weatherData")
                weatherData
            } catch (e: Exception) {
                Log.e(TAG, "Error al obtener clima: ${e.message}")
                e.printStackTrace()
                null
            }
        }
    }
    
    /**
     * Obtiene el clima por nombre de ciudad
     */
    suspend fun getWeatherByCity(cityName: String): WeatherData? {
        return withContext(Dispatchers.IO) {
            try {
                val url = "$BASE_URL?q=$cityName&appid=$API_KEY&units=metric&lang=es"
                Log.d(TAG, "Solicitando clima para ciudad: $cityName")
                
                val response = URL(url).readText()
                val jsonObject = JSONObject(response)
                
                val main = jsonObject.getJSONObject("main")
                val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
                val wind = jsonObject.getJSONObject("wind")
                
                val weatherData = WeatherData(
                    temperature = main.getDouble("temp"),
                    feelsLike = main.getDouble("feels_like"),
                    description = weather.getString("description").capitalize(),
                    humidity = main.getInt("humidity"),
                    windSpeed = wind.getDouble("speed"),
                    cityName = jsonObject.getString("name"),
                    icon = getWeatherEmoji(weather.getString("main"), weather.getString("icon"))
                )
                
                Log.d(TAG, "Clima obtenido para $cityName: $weatherData")
                weatherData
            } catch (e: Exception) {
                Log.e(TAG, "Error al obtener clima para $cityName: ${e.message}")
                e.printStackTrace()
                null
            }
        }
    }
    
    /**
     * Convierte el código de clima de la API a emoji
     */
    private fun getWeatherEmoji(main: String, icon: String): String {
        return when (main.lowercase()) {
            "clear" -> if (icon.contains("d")) "☀️" else "🌙"
            "clouds" -> "☁️"
            "rain" -> "🌧️"
            "drizzle" -> "🌦️"
            "thunderstorm" -> "⛈️"
            "snow" -> "❄️"
            "mist", "fog" -> "🌫️"
            "smoke" -> "💨"
            else -> "🌤️"
        }
    }
}


