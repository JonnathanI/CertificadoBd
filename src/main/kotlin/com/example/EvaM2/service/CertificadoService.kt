package com.example.EvaM2.service

import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class CertificadoService {
    fun generateCertificatePdf(studentName: String, courseName: String, hours: String): ByteArray {
        try {
            val document = Document()
            val outputStream = ByteArrayOutputStream()

            PdfWriter.getInstance(document, outputStream)
            document.open()

            document.add(Paragraph("Certificate of Completion"))
            document.add(Paragraph(" "))
            document.add(Paragraph("This certifies that $studentName"))
            document.add(Paragraph("has successfully completed the course $courseName"))
            document.add(Paragraph("with a total of $hours hours."))

            document.close()
            return outputStream.toByteArray()
        } catch (e: Exception) {
            throw RuntimeException("Error generating certificate PDF: ${e.message}", e)
        }
    }




}