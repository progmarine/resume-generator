package cool.project.generateresume.controller

import cool.project.generateresume.dto.DocumentDto
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.service.DocumentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.*
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Api(tags = ["Document Controller"])
@RestController
class DocumentController(private val documentService: DocumentService) {

    @ApiOperation(value = "Print document name", notes = "Type input and expect to see output")
    @RequestMapping(value = ["/print-doc-name"], method = [RequestMethod.POST])
    fun printDocName(@RequestBody(required = true) document: DocumentDto): String {
        return documentService.docName(document)
    }

    @ApiOperation(value = "Generate resume", notes = "Fill each field for your resume")
    @RequestMapping(
        value = ["/generate-resume"],
        method = [RequestMethod.POST], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    fun generateResume(
        @RequestBody(required = true) resumeDto: ResumeDto,
        response: HttpServletResponse,
        request: HttpServletRequest
    ): ResponseEntity<ByteArray> {

        val doc = documentService.generateResume(resumeDto)

        val contentDisposition = ContentDisposition
            .builder("attachment")
            .filename("resume.pdf", StandardCharsets.UTF_8)
            .build()

        val headers = HttpHeaders()
        headers.contentDisposition = contentDisposition
        return ResponseEntity(doc, headers, HttpStatus.OK)
    }

}