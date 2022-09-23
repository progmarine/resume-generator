package cool.project.generateresume.controller

import cool.project.generateresume.dto.DocumentDto
import cool.project.generateresume.dto.ResumeDto
import cool.project.generateresume.enums.DocumentStatus
import cool.project.generateresume.rest.CustomWebResponse
import cool.project.generateresume.service.DocumentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.io.FileInputStream
import java.io.FileOutputStream
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
        method = [RequestMethod.POST], produces = [MediaType.APPLICATION_PDF_VALUE]
    )
    fun generateResume(
        @RequestBody(required = true) resumeDto: ResumeDto,
        response: HttpServletResponse,
        request: HttpServletRequest
    ): ResponseEntity<InputStreamResource> {
        response.contentType = "application/pdf"
        val headers = HttpHeaders()
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf")
        val doc = documentService.generateResume(resumeDto)


        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body( InputStreamResource(doc))
    }

}