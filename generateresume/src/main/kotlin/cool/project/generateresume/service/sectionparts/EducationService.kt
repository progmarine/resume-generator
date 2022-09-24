package cool.project.generateresume.service.sectionparts

import com.itextpdf.text.Document
import com.itextpdf.text.List
import com.itextpdf.text.Paragraph
import cool.project.generateresume.companion.SectionCompanion.Companion.SECTION_NAME_FONT
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.service.Section
import org.springframework.stereotype.Service

@Service
class EducationService : Section {

    override fun produce(document: Document, resume: ResumeDto) {
        val educationSectionName = Paragraph("EDUCATION", SECTION_NAME_FONT)
        document.add(educationSectionName)
        val educationList = List(List.UNORDERED)
        educationList.setListSymbol("• ")
        resume.education.forEach { edu ->
            val info = educationInfo(edu)
            educationList.add(info)
        }
        document.add(educationList)
    }

    private fun educationInfo(education: ResumeDto.Education): String {
        val title = "${education.institutionName} | ${education.dateStart} - ${education.dateEnd} \n"
        val body = if (education.major == null) {
            "${education.degree} " +
                    "\n${education.grade}"
        } else {
            "${education.degree} with major in " +
                    "${education.major}" +
                    "\n${education.grade}"
        }
        return title + body
    }
}