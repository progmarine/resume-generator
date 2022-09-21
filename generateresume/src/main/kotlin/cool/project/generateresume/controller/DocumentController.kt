package cool.project.generateresume.controller

import cool.project.generateresume.exception.IncorrectInputException
import cool.project.generateresume.service.DocumentService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class DocumentController(private val documentService: DocumentService) {

    @ApiOperation(value = "Print some message", notes = "Type input and expect to see output")
    @RequestMapping(value = ["/print"], method = [RequestMethod.POST])
    fun printString(@RequestParam message: String?) : String {
        return message ?: throw IncorrectInputException("Input should be not null and be string")
    }
}