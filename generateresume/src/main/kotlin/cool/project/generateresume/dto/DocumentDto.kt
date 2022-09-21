package cool.project.generateresume.dto

import cool.project.generateresume.enums.DocumentType

data class DocumentDto(
    val docName: String?,
    val docType: DocumentType?
)