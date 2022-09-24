package cool.project.generateresume.service.sectionparts

import com.itextpdf.text.Document
import com.itextpdf.text.List
import com.itextpdf.text.Paragraph
import cool.project.generateresume.companion.SectionCompanion.Companion.SECTION_NAME_FONT
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.service.Section
import org.springframework.stereotype.Service

@Service
class ExperienceService : Section {

    override fun produce(document: Document, resume: ResumeDto) {
        val workSectionName = Paragraph("WORK", SECTION_NAME_FONT)
        document.add(workSectionName)
        val workList = List(List.UNORDERED)
        workList.setListSymbol("• ")
        resume.experience.forEach { work ->
            val info = workInfo(work)
            workList.add(info)
        }
        document.add(workList)
    }

    private fun workInfo(work: ResumeDto.Experience): String {
        val title = "${work.companyName} | ${work.dateStart} - ${work.dateEnd}\n"
        val body = "${work.positionName} \n${work.description}"
        return title + body
    }
}