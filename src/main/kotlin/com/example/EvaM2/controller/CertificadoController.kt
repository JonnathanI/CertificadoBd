package com.example.EvaM2.controller

import com.example.EvaM2.service.CertificadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/certificado")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class CertificadoController {
    @Autowired
    lateinit var certificadoService: CertificadoService

    @GetMapping("/generate/{studentName}/{courseName}/{hours}")
    fun generateCertificate(
        @PathVariable studentName: String,
        @PathVariable courseName: String,
        @PathVariable hours: String
    ): ResponseEntity<ByteArray> {
        val pdfBytes = certificadoService.generateCertificatePdf(studentName, courseName, hours)

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"certificate.pdf\"")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfBytes)
    }



}