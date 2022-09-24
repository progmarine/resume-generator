package cool.project.generateresume.service

import com.itextpdf.text.Chunk
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Paragraph
import cool.project.generateresume.companion.SectionCompanion.Companion.FONT_FULL_NAME
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.service.sectionparts.EducationService
import cool.project.generateresume.service.sectionparts.ExperienceService
import cool.project.generateresume.service.sectionparts.ProjectsService
import cool.project.generateresume.service.sectionparts.SkillsService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SectionService(
    private val educationService: EducationService,
    private val experienceService: ExperienceService,
    private val skillsService: SkillsService,
    private val projectsService: ProjectsService
) {

    fun produceSections(document: Document, resume: ResumeDto) {
        try {
            logger.info("Producing section parts")
            educationService.produce(document, resume)
            document.add(Chunk.NEWLINE)
            experienceService.produce(document, resume)
            document.add(Chunk.NEWLINE)
            skillsService.produce(document, resume)
            document.add(Chunk.NEWLINE)
            projectsService.produce(document, resume)
            logger.info("Completed section producing")
        } catch (e: Exception) {
            logger.error("Something went wrong with producing section parts: ${e.message}")
        }
    }

    fun produceBio(document: Document, resume: ResumeDto) {
        val fullName = Paragraph(resume.name, FONT_FULL_NAME)
        fullName.alignment = Element.ALIGN_CENTER
        document.add(fullName)
        document.add(Chunk.NEWLINE)
        val bio = Paragraph("${resume.contact.email} | ${resume.contact.portfolio} | ${resume.contact.phoneNumber}")
        bio.alignment = Element.ALIGN_CENTER
        document.add(bio)
        document.add(Chunk.NEWLINE)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

}