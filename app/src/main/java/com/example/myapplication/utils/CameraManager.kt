package com.example.myapplication.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraManager(private val context: Context) {
    
    fun createImageFile(context: Context): File {
        Log.d("CameraManager", "Iniciando creación de archivo de imagen")
        
        try {
            // Verificar contexto válido
            if (context == null) {
                throw IllegalArgumentException("Context es null")
            }
            
            // Intentar múltiples ubicaciones de forma segura
            val imageDir = try {
                if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                    val externalDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    if (externalDir != null) {
                        File(externalDir, "ReciclaContigo")
                    } else {
                        Log.w("CameraManager", "ExternalFilesDir es null, usando directorio interno")
                        File(context.filesDir, "images")
                    }
                } else {
                    Log.w("CameraManager", "Almacenamiento externo no disponible, usando interno")
                    File(context.filesDir, "images")
                }
            } catch (e: Exception) {
                Log.w("CameraManager", "Error accediendo a directorio externo: ${e.message}")
                File(context.filesDir, "images")
            }
            
            Log.d("CameraManager", "Directorio de imágenes: ${imageDir.absolutePath}")
            
            // Crear directorio si no existe
            if (!imageDir.exists()) {
                val created = try {
                    imageDir.mkdirs()
                } catch (e: Exception) {
                    Log.e("CameraManager", "Error creando directorio: ${e.message}")
                    false
                }
                Log.d("CameraManager", "Directorio creado: $created")
                if (!created && !imageDir.exists()) {
                    Log.e("CameraManager", "No se pudo crear el directorio: ${imageDir.absolutePath}")
                    throw IOException("No se pudo crear el directorio de imágenes")
                }
            }
            
            // Verificar que el directorio existe y es escribible
            if (!imageDir.canWrite()) {
                Log.e("CameraManager", "Directorio no escribible: ${imageDir.absolutePath}")
                throw IOException("Directorio no escribible")
            }
            
            // Crear nombre único para la imagen de forma segura
            val timeStamp = try {
                SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            } catch (e: Exception) {
                Log.w("CameraManager", "Error creando timestamp: ${e.message}")
                System.currentTimeMillis().toString()
            }
            val imageFileName = "ReciclaContigo_${timeStamp}"
            
            Log.d("CameraManager", "Creando archivo: $imageFileName.jpg")
            
            val imageFile = try {
                File.createTempFile(
                    imageFileName,
                    ".jpg",
                    imageDir
                )
            } catch (e: Exception) {
                Log.e("CameraManager", "Error creando archivo temporal: ${e.message}")
                // Fallback: crear archivo con nombre único
                File(imageDir, "${imageFileName}.jpg")
            }
            
            // Verificar que el archivo fue creado correctamente
            if (!imageFile.exists()) {
                Log.e("CameraManager", "Archivo no existe después de la creación")
                throw IOException("Archivo no fue creado correctamente")
            }
            
            Log.d("CameraManager", "Archivo creado exitosamente: ${imageFile.absolutePath}")
            Log.d("CameraManager", "Tamaño del archivo: ${imageFile.length()} bytes")
            Log.d("CameraManager", "Archivo escribible: ${imageFile.canWrite()}")
            
            return imageFile
            
        } catch (e: Exception) {
            Log.e("CameraManager", "Error al crear archivo de imagen: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
    
    fun deleteImageFile(file: File): Boolean {
        return try {
            val deleted = file.delete()
            Log.d("CameraManager", "Archivo eliminado: $deleted - ${file.absolutePath}")
            deleted
        } catch (e: Exception) {
            Log.e("CameraManager", "Error al eliminar archivo: ${e.message}")
            false
        }
    }
}