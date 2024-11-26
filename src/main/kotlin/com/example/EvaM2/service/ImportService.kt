package com.example.EvaM2.service

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.example.EvaM2.entity.*
import com.example.EvaM2.repository.*

import java.io.InputStream

@Service
class ImportService {

    @Autowired
    private lateinit var studentsRepository: StudentsRepository

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var trainerRepository: TrainerRepository

    @Autowired
    private lateinit var certificateRepository: CertificateRepository

    fun importarDatosDesdeExcel(fileInputStream: InputStream) {
        val workbook = XSSFWorkbook(fileInputStream)
        val sheet = workbook.getSheetAt(0)

        for (row in sheet) {
            if (row.rowNum == 0) continue // Salta la primera fila (encabezado)

            val name = row.getCell(0).stringCellValue
            val dni = row.getCell(1).stringCellValue
            val code = row.getCell(2).stringCellValue
            val link = row.getCell(3).stringCellValue
            val temaArea = row.getCell(4).stringCellValue
            val burden = row.getCell(5).stringCellValue
            val date = row.getCell(6).stringCellValue
            val courseName = row.getCell(7).stringCellValue
            val aim = row.getCell(8).stringCellValue
            val contents = row.getCell(9).stringCellValue
            val hours = row.getCell(10).stringCellValue

            // Crear estudiante
            val student = Students()
            student.name = name
            student.dni = dni
            student.rol = "Rol ejemplo" // Si tienes información sobre el rol, lo puedes asignar aquí
            studentsRepository.save(student)

            // Crear curso
            val course = Course()
            course.name = courseName
            course.link = link
            course.code = code
            course.aim = aim
            course.contents = contents
            course.students = student // Relaciona el curso con el estudiante
            courseRepository.save(course)

            // Crear trainer
            val trainer = Trainer()
            trainer.name = temaArea
            trainer.area = temaArea
            trainer.burden = burden
            trainer.course = course // Relaciona el trainer con el curso
            trainerRepository.save(trainer)

            // Crear certificado
            val certificate = Certificate()
            certificate.hours = hours
            certificate.date = date
            certificate.trainer = trainer // Relaciona el certificado con el trainer
            certificateRepository.save(certificate)
        }

        workbook.close()
    }
}
