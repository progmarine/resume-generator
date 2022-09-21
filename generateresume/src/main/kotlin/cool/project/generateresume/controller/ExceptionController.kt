package cool.project.generateresume.controller

import com.fasterxml.jackson.databind.ObjectMapper
import cool.project.generateresume.exception.IncorrectInputException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import springfox.documentation.annotations.ApiIgnore
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/")
@ApiIgnore
@ControllerAdvice
class ExceptionController {

    @GetMapping("/")
    fun rootToSwaggerUiRedirect(): RedirectView {
        return RedirectView("/swagger-ui/")
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleRuntimeException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: RuntimeException
    ) {
        logger.error("Server error", ex)
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(
            ObjectMapper().writeValueAsString(
                CustomExceptionResponse(
                    status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    error = ex.message ?: ex.localizedMessage,
                    path = request.requestURI
                )
            )
        )
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    fun handleIncorrectInputException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: IncorrectInputException
    ) {
        logger.error("Server error", ex)
        response.status = HttpStatus.EXPECTATION_FAILED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(
            ObjectMapper().writeValueAsString(
                CustomExceptionResponse(
                    status = HttpStatus.EXPECTATION_FAILED.value(),
                    error = ex.message ?: ex.localizedMessage,
                    path = request.requestURI
                )
            )
        )
    }

    private inner class CustomExceptionResponse(
        val timestamp: String = LocalDateTime.now().toString(),
        val status: Int,
        val error: String,
        val path: String
    )

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

}