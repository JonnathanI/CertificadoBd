package com.example.EvaM2.repository

import com.example.EvaM2.entity.Students
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface StudentsRepository:JpaRepository<Students, Long> {
    fun findById(id: Long?): Optional<Students>
    fun findByDni(dni: String): Students?

}