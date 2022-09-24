package cool.project.generateresume.service

import com.itextpdf.text.Document
import com.itextpdf.text.List
import com.itextpdf.text.Paragraph
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.service.SectionCompanion.Companion.SECTION_ELEMENT_FONT
import cool.project.generateresume.service.SectionCompanion.Companion.SECTION_NAME_FONT
import org.springframework.stereotype.Service

@Service
class EducationService : Section {

    override fun produce(document: Document, resume: ResumeDto) {
        val educationSectionName = Paragraph("EDUCATION", SECTION_NAME_FONT)
        document.add(educationSectionName)
        val educationList = List(List.UNORDERED)
        resume.education.forEach { edu ->
            val info = educationInfo(edu)
            educationList.add(Paragraph(info, SECTION_ELEMENT_FONT))
        }
        document.add(educationList)
    }

    private fun educationInfo(education: ResumeDto.Education): String {
        return "${education.institutionName} \n" +
                "${education.degree} \n" +
                "${education.major} \n" +
                "${education.dateStart} \n" +
                "${education.dateEnd} \n" +
                "${education.grade}"
    }
}