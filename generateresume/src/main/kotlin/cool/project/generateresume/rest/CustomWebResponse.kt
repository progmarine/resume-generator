package cool.project.generateresume.rest

import cool.project.generateresume.enums.DocumentStatus

data class CustomWebResponse(
    val action: DocumentStatus? = null,
    val status: Int
)