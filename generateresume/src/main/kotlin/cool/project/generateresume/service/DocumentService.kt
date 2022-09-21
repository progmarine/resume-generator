package cool.project.generateresume.service

import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfWriter
import cool.project.generateresume.dto.DocumentDto
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.exception.IncorrectInputException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

@Service
class DocumentService {

    fun docName(document: DocumentDto): String {
        if (!document.docName.isNullOrEmpty()) {
            return document.docName
        }
        throw IncorrectInputException("Document name should not be null or empty")
    }

    fun generateResume(resume: ResumeDto): ByteArrayInputStream {
        val document = Document()
        val out = ByteArrayOutputStream()
        try {
            PdfWriter.getInstance(document, out)
            document.open()

            val fontFullName = FontFactory.getFont(
                FontFactory.COURIER, 14f,
                BaseColor.BLACK
            )
            val fontSectionName = FontFactory.getFont(
                FontFactory.COURIER, 12f,
                BaseColor.BLACK
            )
            val fontSectionElements = FontFactory.getFont(
                FontFactory.COURIER, 10f,
                BaseColor.BLACK
            )
            val fullName = Paragraph(resume.name, fontFullName)
            fullName.alignment = Element.ALIGN_CENTER
            document.add(fullName)
            document.add(Chunk.NEWLINE)

            resume.education.forEach { edu ->
                val listOfEducation = ListItem(edu.institutionName, fontSectionName)
                listOfEducation.alignment = Element.ALIGN_LEFT
            }
            //TODO: Create list of education, experience, chunk of skills
            document.close()
        } catch (e: Exception) {
            throw DocumentException(e.message)
        }
        return ByteArrayInputStream(out.toByteArray())
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}