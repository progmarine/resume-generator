package cool.project.generateresume.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResumeDto(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("contact")
    val contact: Contact,
    @JsonProperty("education_info")
    val education: List<Education>,
    @JsonProperty("experience_info")
    val experience: List<Experience>,
    @JsonProperty("skills")
    val skills: List<String>,
    @JsonProperty("projects")
    val projects: List<Project>?
) {
    data class Contact(
        @JsonProperty("email")
        val email: String,
        @JsonProperty("phone_number")
        val phoneNumber: String,
        @JsonProperty("portfolio_link")
        val portfolio: String?
    )

    data class Education(
        @JsonProperty("institution_name")
        val institutionName: String,
        @JsonProperty("date_start")
        val dateStart: LocalDate,
        @JsonProperty("date_end")
        val dateEnd: LocalDate? = null,
        @JsonProperty("degree")
        val degree: String,
        @JsonProperty("major")
        val major: String? = null,
        @JsonProperty("grade")
        val grade: Double? = null
    )

    data class Experience(
        @JsonProperty("company_name")
        val companyName: String,
        @JsonProperty("date_start")
        val dateStart: LocalDate,
        @JsonProperty("date_end")
        val dateEnd: LocalDate? = null,
        @JsonProperty("position_name")
        val positionName: String,
        @JsonProperty("job_description")
        val description: String? = null
    )

    data class Project(
        @JsonProperty("project_name")
        val name: String,
        @JsonProperty("project_description")
        val description: String? = null,
        @JsonProperty("project_link")
        val link: String? = null,
    )
}