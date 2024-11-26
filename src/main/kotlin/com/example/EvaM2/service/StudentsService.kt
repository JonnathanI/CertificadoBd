package com.example.EvaM2.service

import com.example.EvaM2.entity.Course
import com.example.EvaM2.entity.Students
import com.example.EvaM2.repository.CourseRepository
import com.example.EvaM2.repository.StudentsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class StudentsService {
    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    lateinit var studentsRepository: StudentsRepository

    fun list(): List<Students> {
        return studentsRepository.findAll()
    }

    fun save(students: Students): Students {
        return studentsRepository.save(students)
    }

    fun update(students: Students): Students {
        try {
            studentsRepository.findById(students.id)
                ?: throw Exception("Ya existe el id")
            return studentsRepository.save(students)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(students: Students): Students {
        val existingStudents = studentsRepository.findById(students.id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante con id ${students.id} no encontrado") }
        existingStudents.name = students.name
        return studentsRepository.save(existingStudents)
    }

    fun delete(id: Long) {
        val student = studentsRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no Existe con el Id: $id") }
        studentsRepository.delete(student)
    }

    fun findById(id: Long): Students? {
        return studentsRepository.findById(id).orElse(null)
    }
    fun findByDni(dni: String): Students? {
        return studentsRepository.findByDni(dni)
    }

}
