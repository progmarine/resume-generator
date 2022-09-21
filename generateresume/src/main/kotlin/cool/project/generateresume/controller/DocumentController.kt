package cool.project.generateresume.controller

import cool.project.generateresume.dto.DocumentDto
import cool.project.generateresume.service.DocumentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@Api(tags = ["Document Controller"])
@RestController
class DocumentController(private val documentService: DocumentService) {

    @ApiOperation(value = "Print document name", notes = "Type input and expect to see output")
    @RequestMapping(value = ["/print-doc-name"], method = [RequestMethod.POST])
    fun printDocName(@RequestBody(required = true) document: DocumentDto): String {
        return documentService.docName(document)
    }
}