package cool.project.generateresume.service

import cool.project.generateresume.dto.DocumentDto
import cool.project.generateresume.exception.IncorrectInputException
import org.springframework.stereotype.Service

@Service
class DocumentService {

    fun docName(document: DocumentDto): String {
        if (!document.docName.isNullOrEmpty()) {
            return document.docName
        }
        throw IncorrectInputException("Document name should not be null or empty")
    }
}