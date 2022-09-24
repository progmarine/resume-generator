package cool.project.generateresume.service

import com.itextpdf.text.Document
import com.itextpdf.text.List
import com.itextpdf.text.Paragraph
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.service.SectionCompanion.Companion.SECTION_ELEMENT_FONT
import cool.project.generateresume.service.SectionCompanion.Companion.SECTION_NAME_FONT
import org.springframework.stereotype.Service

@Service
class ProjectsService : Section {

    override fun produce(document: Document, resume: ResumeDto) {
        val projects = Paragraph("PROJECTS", SECTION_NAME_FONT)
        document.add(projects)
        val projectList = List(List.UNORDERED)
        resume.projects?.forEach { project ->
            val info = projectInfo(project)
            projectList.add(Paragraph(info, SECTION_ELEMENT_FONT))
        }
        document.add(projectList)
    }

    private fun projectInfo(project: ResumeDto.Project) = "${project.name} \n" +
            "${project.link} \n" +
            "${project.description}"
}