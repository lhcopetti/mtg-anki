package com.copetti.mtg.deck.provider.anki

import com.copetti.mtg.deck.domain.model.FlashCard
import org.springframework.stereotype.Component

@Component
class AnkiCreateFlashCardEntry {

    fun create(flashCard: FlashCard): String {
        val front = format(flashCard.front)
        val back = format(flashCard.back)
        val tags = flashCard.tags.joinToString(separator = ",")

        val result = StringBuilder()
        result.append("${front}${FIELD_SEPARATOR}${back}")

        if (tags.isNotEmpty())
            result.append("\t${tags}")

        return result.appendLine().toString()
    }

    private fun format(value: String) = value
        .replace("\t", " ")
        .replace("\n", HTML_NEW_LINE)

    companion object {
        private const val HTML_NEW_LINE = "<br>"
        private const val FIELD_SEPARATOR = "\t"
    }
}