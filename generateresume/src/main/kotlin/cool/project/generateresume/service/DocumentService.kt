package cool.project.generateresume.service

import com.itextpdf.text.*
import com.itextpdf.text.List
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

    fun generateResume(resume: ResumeDto): ByteArray {
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
            val bio = Paragraph("${resume.contact.email}, ${resume.contact.portfolio}, ${resume.contact.phoneNumber}")
            bio.alignment = Element.ALIGN_CENTER
            document.add(bio)
            val educationSection = Paragraph("EDUCATION", fontSectionName)
            document.add(educationSection)
            val educationList = List()
            resume.education.forEach { edu ->
                educationList.add(
                    Chunk(
                        "${edu.institutionName} \n" +
                                "${edu.degree} \n" +
                                "${edu.major} \n" +
                                "${edu.dateStart} \n" +
                                "${edu.dateEnd} \n" +
                                "${edu.grade}",
                        fontSectionElements
                    )
                )
                document.add(educationList)
            }
            val workSection = Paragraph("WORK", fontSectionName)
            document.add(workSection)
            val workList = List()
            resume.experience.forEach { work ->
                workList.add(
                    Chunk(
                        "${work.companyName} \n" +
                                "${work.positionName} \n" +
                                "${work.dateStart} \n" +
                                "${work.dateEnd} \n" +
                                "${work.description}",
                        fontSectionElements
                    )
                )
                document.add(workList)
            }
            val skills = Paragraph("SKILLS", fontSectionName)
            document.add(skills)
            val skillsList = List()
            skillsList.add(Chunk(resume.skills.joinToString(separator = " | ") { it }, fontSectionElements))
            document.add(skillsList)
            val projects = Paragraph("PROJECTS", fontSectionName)
            document.add(projects)
            val projectList = List()
            resume.projects?.forEach { project ->
                projectList.add(
                    Chunk(
                        "${project.name} \n" +
                                "${project.link} \n" +
                                "${project.description}",
                        fontSectionElements
                    )
                )
                document.add(projectList)
            }
            document.close()
        } catch (e: Exception) {
            throw DocumentException(e.message)
        }
        return out.toByteArray()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}