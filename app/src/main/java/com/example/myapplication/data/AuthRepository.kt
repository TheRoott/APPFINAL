package com.example.myapplication.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

data class UserCredentials(
    val email: String,
    val password: String,
    val name: String,
    val location: String = "Ventanilla, Callao"
)

class AuthRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("recicla_contigo_users", Context.MODE_PRIVATE)
    private val TAG = "AuthRepository"
    
    init {
        // Crear algunos usuarios de prueba si es la primera vez
        if (!hasUsers()) {
            createDefaultUsers()
        }
    }
    
    private fun hasUsers(): Boolean {
        return prefs.getAll().isNotEmpty()
    }
    
    private fun createDefaultUsers() {
        // Usuario de prueba 1
        registerUser(
            email = "usuario@reciclacontigo.com",
            password = "123456",
            name = "Usuario Demo"
        )
        
        // Usuario de prueba 2
        registerUser(
            email = "admin@reciclacontigo.com",
            password = "admin123",
            name = "Administrador"
        )
        
        // Usuario de prueba 3
        registerUser(
            email = "test@gmail.com",
            password = "test123",
            name = "Usuario Test"
        )
        
        Log.d(TAG, "Usuarios de prueba creados exitosamente")
    }
    
    /**
     * Registra un nuevo usuario
     * @return true si el registro fue exitoso, false si el email ya existe
     */
    fun registerUser(email: String, password: String, name: String, location: String = "Ventanilla, Callao"): Boolean {
        if (userExists(email)) {
            Log.w(TAG, "El usuario con email $email ya existe")
            return false
        }
        
        // Guardar credenciales
        prefs.edit().apply {
            putString("user_${email}_password", password)
            putString("user_${email}_name", name)
            putString("user_${email}_location", location)
            putLong("user_${email}_registered_at", System.currentTimeMillis())
            apply()
        }
        
        Log.d(TAG, "Usuario registrado exitosamente: $email")
        return true
    }
    
    /**
     * Valida las credenciales del usuario
     * @return UserCredentials si las credenciales son válidas, null si no
     */
    fun validateCredentials(email: String, password: String): UserCredentials? {
        if (!userExists(email)) {
            Log.w(TAG, "Usuario no encontrado: $email")
            return null
        }
        
        val storedPassword = prefs.getString("user_${email}_password", null)
        
        if (storedPassword == null || storedPassword != password) {
            Log.w(TAG, "Contraseña incorrecta para: $email")
            return null
        }
        
        // Credenciales válidas, retornar datos del usuario
        val name = prefs.getString("user_${email}_name", "Usuario") ?: "Usuario"
        val location = prefs.getString("user_${email}_location", "Ventanilla, Callao") ?: "Ventanilla, Callao"
        
        Log.d(TAG, "Credenciales válidas para: $email")
        return UserCredentials(email, password, name, location)
    }
    
    /**
     * Verifica si un usuario existe
     */
    fun userExists(email: String): Boolean {
        return prefs.contains("user_${email}_password")
    }
    
    /**
     * Obtiene todos los emails registrados (para debugging)
     */
    fun getAllRegisteredEmails(): List<String> {
        val emails = mutableListOf<String>()
        prefs.all.keys.forEach { key ->
            if (key.startsWith("user_") && key.endsWith("_password")) {
                val email = key.removePrefix("user_").removeSuffix("_password")
                emails.add(email)
            }
        }
        return emails
    }
    
    /**
     * Obtiene el nombre de un usuario por su email
     */
    fun getUserName(email: String): String? {
        return prefs.getString("user_${email}_name", null)
    }
}


