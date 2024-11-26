package com.example.EvaM2.controller

import com.example.EvaM2.service.ImportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/import")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class ImportController {

    @Autowired
    private lateinit var importService: ImportService

    @PostMapping("/excel")
    fun importarExcel(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        return try {
            val fileInputStream = file.inputStream
            importService.importarDatosDesdeExcel(fileInputStream)
            ResponseEntity.ok("Datos importados exitosamente")
        } catch (e: Exception) {
            ResponseEntity.status(500).body("Error al importar los datos: ${e.message}")
        }
    }
}
