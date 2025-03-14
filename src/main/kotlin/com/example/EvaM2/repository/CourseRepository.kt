package com.example.EvaM2.repository

import com.example.EvaM2.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: JpaRepository<Course, Long> {

    // Método para buscar un curso por su ID
    fun findById(id: Long?): Course?
    fun findByCode(code: String): Course?
    // Método para buscar los cursos por el DNI del estudiante y el código del curso
    fun findByStudents_DniAndCode(dni: String, courseCode: String): List<Course>?

    // Método para buscar los cursos por el DNI del estudiante y el nombre del curso
    fun findByStudents_DniAndNameContaining(dni: String, courseName: String): List<Course>?
    fun findByStudentsDni(dni: String): List<Course>

}
