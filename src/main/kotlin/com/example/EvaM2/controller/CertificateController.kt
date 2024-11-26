package com.example.EvaM2.controller

import com.example.EvaM2.entity.Certificate
import com.example.EvaM2.service.CertificateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@RestController
@RequestMapping("/certificate")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class CertificateController {

    @Autowired
    lateinit var certificateService: CertificateService

    // List all certificates
    @GetMapping
    fun list(): List<Certificate> {
        return certificateService.list()
    }

    // Save a new certificate
    @PostMapping
    fun save(@RequestBody certificate: Certificate): Certificate {
        return certificateService.save(certificate)
    }

    @PutMapping
    fun update(@RequestBody certificate: Certificate): ResponseEntity<Certificate> {
        return ResponseEntity(certificateService.updateName(certificate), HttpStatus.OK)
    }

    // Delete a certificate by its ID
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        return try {
            certificateService.delete(id)
            ResponseEntity("Certificate deleted successfully", HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity("Certificate not found", HttpStatus.NOT_FOUND)
        }
    }


}
