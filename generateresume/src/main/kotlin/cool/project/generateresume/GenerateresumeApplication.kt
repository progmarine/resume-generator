package cool.project.generateresume

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan
class GenerateresumeApplication

fun main(args: Array<String>) {
	runApplication<GenerateresumeApplication>(*args)
}
