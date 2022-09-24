package cool.project.generateresume.service.sectionparts

import com.itextpdf.text.Document
import com.itextpdf.text.List
import com.itextpdf.text.Paragraph
import cool.project.generateresume.companion.SectionCompanion.Companion.SECTION_NAME_FONT
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.service.Section
import org.springframework.stereotype.Service

@Service
class ProjectsService : Section {

    override fun produce(document: Document, resume: ResumeDto) {
        val projects = Paragraph("PROJECTS", SECTION_NAME_FONT)
        document.add(projects)
        val projectList = List(List.UNORDERED)
        projectList.setListSymbol("• ")
        resume.projects?.forEach { project ->
            val info = projectInfo(project)
            projectList.add(info)
        }
        document.add(projectList)
    }

    private fun projectInfo(project: ResumeDto.Project) = "${project.name} \n" +
            "${project.link} \n" +
            "${project.description}"
}