package cool.project.generateresume.service

import com.itextpdf.text.BaseColor
import com.itextpdf.text.Font
import com.itextpdf.text.FontFactory

class SectionCompanion {

    companion object {
        val SECTION_NAME_FONT: Font = FontFactory.getFont(
            FontFactory.TIMES, 12f,
            BaseColor.BLACK
        )

        val SECTION_ELEMENT_FONT: Font = FontFactory.getFont(
            FontFactory.TIMES, 10f,
            BaseColor.BLACK
        )

        val FONT_FULL_NAME = FontFactory.getFont(
            FontFactory.TIMES, 14f,
            BaseColor.BLACK
        )
    }
}