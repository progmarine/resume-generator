package cool.project.generateresume.service

import com.itextpdf.text.Document
import com.itextpdf.text.List
import com.itextpdf.text.Paragraph
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.service.SectionCompanion.Companion.SECTION_ELEMENT_FONT
import cool.project.generateresume.service.SectionCompanion.Companion.SECTION_NAME_FONT
import org.springframework.stereotype.Service

@Service
class ExperienceService : Section {

    override fun produce(document: Document, resume: ResumeDto) {
        val workSectionName = Paragraph("WORK", SECTION_NAME_FONT)
        document.add(workSectionName)
        val workList = List(List.UNORDERED)
        resume.experience.forEach { work ->
            val info = workInfo(work)
            workList.add(Paragraph(info, SECTION_ELEMENT_FONT))
        }
        document.add(workList)
    }

    private fun workInfo(work: ResumeDto.Experience) = "${work.companyName} \n" +
            "${work.positionName} \n" +
            "${work.dateStart} \n" +
            "${work.dateEnd} \n" +
            "${work.description}"
}