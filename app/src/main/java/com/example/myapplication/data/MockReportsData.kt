package com.example.myapplication.data

import com.example.myapplication.utils.LocationData
import kotlin.random.Random

object MockReportsData {
    
    // Calles reales de Ventanilla
    private val ventanillaStreets = listOf(
        "Calle Las Palmeras", "Av. Néstor Gambetta", "Calle Los Pinos",
        "Av. Playa Márquez", "Calle Santa Rosa", "Av. Antúnez de Mayolo",
        "Calle Los Cedros", "Calle San Martín", "Av. Pachacútec",
        "Calle Las Flores", "Av. Bahía Blanca", "Calle Los Olivos",
        "Calle Francisco Bolognesi", "Av. Micaela Bastidas", "Calle José Olaya",
        "Av. Los Libertadores", "Calle Miguel Grau", "Av. Universitaria",
        "Calle Las Rosas", "Av. Industrial", "Calle Los Jazmines",
        "Calle Alfonso Ugarte", "Av. Tomás Valle", "Calle Sarita Colonia",
        "Av. Venezuela", "Calle Los Geranios", "Calle Ramón Castilla",
        "Av. Elmer Faucett", "Calle Las Orquídeas", "Calle Túpac Amaru",
        "Av. Óscar R. Benavides", "Calle Los Claveles", "Av. Perú",
        "Calle Las Camelias", "Av. Argentina", "Calle Los Sauces",
        "Calle Andrés Avelino Cáceres", "Av. Próceres", "Calle Las Begonias",
        "Calle José Gálvez", "Av. Sol de Oro", "Calle Las Magnolias",
        "Av. Los Andes", "Calle Las Gardenias", "Calle Juan Pablo II",
        "Av. Los Álamos", "Calle Las Violetas", "Calle Los Eucaliptos",
        "Av. Chinchaysuyo", "Calle Las Petunias", "Av. Antisuyo"
    )
    
    private val reportTitles = listOf(
        "Acumulación de basura", "Contenedor desbordado", "Residuos en la vía pública",
        "Desperdicios en parque", "Basura en esquina", "Montículo de desechos",
        "Contaminación del aire", "Humo de quema de basura", "Emisiones industriales",
        "Olores fétidos", "Gases contaminantes", "Polución atmosférica",
        "Agua contaminada", "Derrame de líquidos", "Aguas residuales",
        "Río contaminado", "Canal con desperdicios", "Playa sucia",
        "Árbol talado ilegalmente", "Deforestación zona verde", "Corte de árboles",
        "Desmonte ilegal", "Vegetación dañada", "Tala en área protegida",
        "Perro abandonado", "Maltrato animal", "Animales en peligro",
        "Ave herida", "Mascota extraviada", "Fauna en riesgo",
        "Escombros abandonados", "Muebles en vía pública", "Chatarra acumulada",
        "Desmonte de construcción", "Residuos peligrosos", "Material tóxico"
    )
    
    private val reportDescriptions = listOf(
        "Se observa gran cantidad de residuos acumulados en el área",
        "Situación que requiere atención inmediata de las autoridades",
        "Los vecinos reportan molestias por la situación",
        "Problema ambiental que afecta a la comunidad",
        "Necesita intervención urgente para evitar mayores daños",
        "La situación ha empeorado en los últimos días",
        "Vecinos solicitan pronta solución al problema",
        "Afecta la salud y bienestar de los residentes",
        "Requiere limpieza y saneamiento del área",
        "Problema recurrente en esta zona",
        "Se necesita coordinación con el municipio",
        "Situación que perjudica el medio ambiente",
        "Los habitantes demandan acción inmediata",
        "Problema que se viene arrastrando hace semanas",
        "Urge atención de las autoridades competentes",
        "Afecta la imagen del distrito",
        "Genera riesgos para la salud pública",
        "Requiere intervención de servicios de limpieza",
        "Situación crítica que no puede esperar",
        "Vecinos organizados solicitan apoyo"
    )
    
    fun generateRandomReports(count: Int = 100): List<EnvironmentalReport> {
        val reports = mutableListOf<EnvironmentalReport>()
        val categories = ReportCategory.values()
        val statuses = ReportStatus.values()
        
        repeat(count) { index ->
            val category = categories[Random.nextInt(categories.size)]
            val status = statuses[Random.nextInt(statuses.size)]
            val street = ventanillaStreets[Random.nextInt(ventanillaStreets.size)]
            
            // Coordenadas aleatorias dentro de Ventanilla
            // Latitud: -11.85 a -11.90
            // Longitud: -77.09 a -77.13
            val latitude = -11.85 + (Random.nextDouble() * 0.05)
            val longitude = -77.09 + (Random.nextDouble() * 0.04)
            
            val title = reportTitles[Random.nextInt(reportTitles.size)]
            val description = reportDescriptions[Random.nextInt(reportDescriptions.size)]
            
            reports.add(
                EnvironmentalReport(
                    id = "report_${System.currentTimeMillis()}_$index",
                    category = category,
                    title = title,
                    description = description,
                    location = LocationData(
                        latitude = latitude,
                        longitude = longitude,
                        address = "$street, Ventanilla, Lima, Perú"
                    ),
                    status = status,
                    ecoPoints = category.ecoPoints,
                    timestamp = System.currentTimeMillis() - (Random.nextLong(0, 30L * 24 * 60 * 60 * 1000)), // Últimos 30 días
                    userId = "user_${Random.nextInt(1000, 9999)}"
                )
            )
        }
        
        return reports.sortedByDescending { it.timestamp }
    }
    
    // Reportes pre-generados para uso en la app
    val allReports: List<EnvironmentalReport> by lazy {
        generateRandomReports(100)
    }
}


