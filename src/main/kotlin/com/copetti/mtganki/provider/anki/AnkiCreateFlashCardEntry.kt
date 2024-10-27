package com.copetti.mtganki.provider.anki

import com.copetti.mtganki.domain.usecase.FlashCard
import org.springframework.stereotype.Component

@Component
class AnkiCreateFlashCardEntry {

    fun create(flashCard: FlashCard): String {
        val front = format(flashCard.front)
        val back = format(flashCard.back)
        return "$front$FIELD_SEPARATOR${back}${System.lineSeparator()}"
    }

    private fun format(value: String) = value
        .replace("\t", " ")
        .replace("\n", HTML_NEW_LINE)

    companion object {
        private const val HTML_NEW_LINE = "<br>"
        private const val FIELD_SEPARATOR = "\t"
    }
}