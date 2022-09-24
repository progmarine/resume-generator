package cool.project.generateresume.service

import com.itextpdf.text.Document
import cool.project.generateresume.dto.ResumeDto

interface Section {
    fun produce(document: Document, resume: ResumeDto)
}