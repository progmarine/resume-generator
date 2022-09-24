package cool.project.generateresume.service

import com.itextpdf.text.Document
import com.itextpdf.text.DocumentException
import com.itextpdf.text.pdf.PdfWriter
import cool.project.generateresume.dto.ResumeDto
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class DocumentService(private val sectionService: SectionService) {

    fun generateResume(resume: ResumeDto): ByteArray {
        val document = Document()
        val out = ByteArrayOutputStream()
        try {
            PdfWriter.getInstance(document, out)
            document.open()
            sectionService.produceBio(document, resume)
            sectionService.produceSections(document, resume)
            document.close()
        } catch (e: Exception) {
            throw DocumentException(e.message)
        }
        return out.toByteArray()
    }

}