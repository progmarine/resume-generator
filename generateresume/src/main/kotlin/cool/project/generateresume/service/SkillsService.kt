package cool.project.generateresume.service

import com.itextpdf.text.Document
import com.itextpdf.text.List
import com.itextpdf.text.Paragraph
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.service.SectionCompanion.Companion.SECTION_ELEMENT_FONT
import cool.project.generateresume.service.SectionCompanion.Companion.SECTION_NAME_FONT
import org.springframework.stereotype.Service

@Service
class SkillsService : Section {

    override fun produce(document: Document, resume: ResumeDto) {
        val skills = Paragraph("SKILLS", SECTION_NAME_FONT)
        document.add(skills)
        val skillsList = List(List.UNORDERED)
        skillsList.add(
            Paragraph(
                resume.skills
                    .joinToString(separator = " | ") { it },
                SECTION_ELEMENT_FONT
            )
        )
        document.add(skillsList)
    }
}