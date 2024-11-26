package com.example.EvaM2.service

import com.example.EvaM2.entity.Course
import com.example.EvaM2.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CourseService {
    @Autowired
    lateinit var courseRepository: CourseRepository

    fun list(): List<Course> {
        return courseRepository.findAll()
    }

    fun save(course: Course): Course {
        return courseRepository.save(course)
    }

    fun update(course: Course): Course {
        try {
            courseRepository.findById(course.id)?: throw Exception("Curso no Encontrado")
            return courseRepository.save(course)
        }catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(course: Course): Course {
        try {

            var response = courseRepository.findById(course.id) ?: throw Exception("Ya existe este ID")
            response.apply {
                name=course.name

            }
            return courseRepository.save(response)
        }
        catch(ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun  delete(id: Long) {
        try {

            var response = courseRepository.findById(id).orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no Encontrado con el Id:  $id")}
            courseRepository.delete(response)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el Curso", ex)
        }
    }
    fun getCourseById(id: Long): Course? {
        return courseRepository.findById(id).orElse(null)
    }
    fun getCoursesByStudent(dni: String, courseCode: String?, courseName: String?): List<Course> {
        // Si se proporciona el código de curso
        if (!courseCode.isNullOrEmpty()) {
            return courseRepository.findByStudents_DniAndCode(dni, courseCode) ?: emptyList()
        }

        // Si se proporciona el nombre del curso
        if (!courseName.isNullOrEmpty()) {
            return courseRepository.findByStudents_DniAndNameContaining(dni, courseName) ?: emptyList()
        }

        // Si no se proporciona ningún parámetro, se devuelve una lista vacía
        return emptyList()
    }
    fun getCoursesByStudent(dni: String): List<Course> {
        return courseRepository.findByStudentsDni(dni)
    }
}