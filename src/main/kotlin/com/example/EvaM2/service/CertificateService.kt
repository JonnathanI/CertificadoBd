package com.example.EvaM2.service

import com.example.EvaM2.entity.Certificate
import com.example.EvaM2.repository.CertificateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CertificateService {
    @Autowired
    lateinit var certificateRepository: CertificateRepository

    fun list(): List<Certificate> {
        return certificateRepository.findAll()
    }

    fun save(certificate: Certificate): Certificate {
        return certificateRepository.save(certificate)
    }

    fun update(certificate: Certificate): Certificate {
        try {
            certificateRepository.findById(certificate.id)?: throw Exception("Certificado no Encontrado")
            return certificateRepository.save(certificate)
        }catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(certificate: Certificate): Certificate {
        try {

            var response = certificateRepository.findById(certificate.id) ?: throw Exception("Ya existe este ID")
            response.apply {
                date=certificate.date

            }
            return certificateRepository.save(response)
        }
        catch(ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun  delete(id: Long) {
        try {

            var response = certificateRepository.findById(id).orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado no Encontrado con el Id:  $id")}
            certificateRepository.delete(response)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el Certificado", ex)
        }
    }
    fun getById(id: Long): Certificate? {
        return certificateRepository.findById(id).orElse(null) // Devuelve el certificado o null si no existe
    }

}