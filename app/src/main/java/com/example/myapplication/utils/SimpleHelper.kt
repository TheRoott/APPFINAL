package com.example.myapplication.utils

import android.content.Context
import android.os.Build
import android.util.Log

object SimpleHelper {
    
    private const val TAG = "SimpleHelper"
    
    /**
     * Verifica si el dispositivo es compatible
     */
    fun isCompatible(): Boolean {
        return Build.VERSION.SDK_INT >= 33 // Android 13+
    }
    
    /**
     * Obtiene información básica del dispositivo
     */
    fun getDeviceInfo(): String {
        return """
            Dispositivo: ${Build.MANUFACTURER} ${Build.MODEL}
            Android: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})
            Compatible: ${isCompatible()}
        """.trimIndent()
    }
    
    /**
     * Log básico de información
     */
    fun logDeviceInfo(context: Context) {
        Log.d(TAG, "=== INFORMACIÓN DEL DISPOSITIVO ===")
        Log.d(TAG, getDeviceInfo())
        
        if (isCompatible()) {
            Log.d(TAG, "✅ Dispositivo compatible con Recicla Contigo")
        } else {
            Log.w(TAG, "⚠️ Dispositivo no compatible - requiere Android 13+")
        }
    }
}



