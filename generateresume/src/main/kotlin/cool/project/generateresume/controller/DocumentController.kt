package cool.project.generateresume.controller

import cool.project.generateresume.service.DocumentService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.ApiInfoBuilder

import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact


@RestController
class DocumentController(private val documentService: DocumentService) {

    @ApiOperation(value = "doc header...", notes = "detailed doc...")
    @RequestMapping(value = ["/print"], method = [RequestMethod.POST])
    fun printString(@RequestParam message: String) : String {
        return message
    }
}