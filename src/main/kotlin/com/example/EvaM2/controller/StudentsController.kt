package com.example.EvaM2.controller

import com.example.EvaM2.entity.Course
import com.example.EvaM2.entity.Students
import com.example.EvaM2.repository.CourseRepository
import com.example.EvaM2.service.CourseService
import com.example.EvaM2.service.StudentsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/student")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class StudentsController {

    @Autowired
    private lateinit var courseService: CourseService

    @Autowired
    lateinit var courseRepository: CourseRepository

    @Autowired
    lateinit var studentsService: StudentsService


    // Este endpoint obtiene la lista de estudiantes
    @GetMapping
    fun list(): List<Students> {
        return studentsService.list()
    }

    // Este endpoint obtiene un estudiante por su ID
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<out Any> {
        val student = studentsService.findById(id)
        return if (student != null) {
            ResponseEntity.ok(student)
        } else {
            ResponseEntity("Estudiante no encontrado", HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/dni/{dni}")
    fun getByDni(@PathVariable dni: String): ResponseEntity<out Any> {
        val student = studentsService.findByDni(dni)
        return if (student != null) {
            ResponseEntity.ok(student)
        } else {
            ResponseEntity("Estudiante no encontrado", HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/courses/{dni}")
    fun getCoursesByStudent(
        @PathVariable dni: String,
        @RequestParam(required = false) courseCode: String?,
        @RequestParam(required = false) courseName: String?
    ): List<Course> {
        return courseService.getCoursesByStudent(dni, courseCode, courseName)
    }

    // Endpoint para guardar un nuevo estudiante
    @PostMapping
    fun save(@RequestBody students: Students): Students {
        return studentsService.save(students)
    }

    // Endpoint para actualizar un estudiante por ID
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?, @RequestBody students: Students?): ResponseEntity<Students> {
        val updatedStudents = studentsService.update(students!!)
        return ResponseEntity.ok(updatedStudents)
    }

    // Endpoint para actualizar parcialmente un estudiante
    @PatchMapping("/{id}")
    fun patch(@PathVariable id: Long?, @RequestBody students: Students?): ResponseEntity<Students> {
        val updatedStudents = studentsService.updateName(students!!)
        return ResponseEntity.ok(updatedStudents)
    }

    // Endpoint para eliminar un estudiante por ID
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        studentsService.delete(id)
        return ResponseEntity("Estudiante Eliminado", HttpStatus.OK)
    }

        @GetMapping("/student-details/{dni}")
        fun getStudentWithCourses(@PathVariable dni: String): ResponseEntity<Any> {
            val student = studentsService.findByDni(dni)
            if (student == null) {
                return ResponseEntity("Estudiante no encontrado", HttpStatus.NOT_FOUND)
            }

            // Obtener los cursos relacionados con el estudiante
            val courses = courseService.getCoursesByStudent(dni)

            // Responder con los detalles del estudiante y los cursos
            val response = mapOf(
                "student" to student,
                "courses" to courses
            )

            return ResponseEntity.ok(response)
        }

}





