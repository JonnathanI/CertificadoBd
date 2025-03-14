package com.example.EvaM2.repository

import com.example.EvaM2.entity.Certificate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CertificateRepository: JpaRepository<Certificate, Long>{
    fun findById(id: Long?): Certificate?
    fun findByCourseId(courseId: Long): Certificate?
}