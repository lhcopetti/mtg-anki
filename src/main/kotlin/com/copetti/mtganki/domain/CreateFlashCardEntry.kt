package com.copetti.mtganki.domain

import com.copetti.mtganki.domain.card.MagicCard
import com.copetti.mtganki.domain.card.VocabularyStudyCard
import org.springframework.stereotype.Service


data class CreateFlashCardEntryRequest(
    val vocabularyStudyCard: VocabularyStudyCard
)

data class FlashCard(
    val front: String,
    val back: String
)

@Service
class CreateFlashCardEntry {

    fun create(request: CreateFlashCardEntryRequest): FlashCard {
        val front = buildFront(request)
        val back = buildBack(request)
        return FlashCard(front = front, back = back)
    }

    private fun buildFront(request: CreateFlashCardEntryRequest) = request.vocabularyStudyCard.vocabulary

    private fun buildBack(request: CreateFlashCardEntryRequest): String {
        val result = StringBuilder()
        result.appendLine(request.vocabularyStudyCard.definition.reading)
        request.vocabularyStudyCard.definition.definitions.forEach(result::appendLine)
        result.appendLine()
        result.appendLine()

        getSampleSentence(request)?.let {
            result.appendLine(it.original)
            result.appendLine(it.translation)
        }
        return result.toString()
    }

    private fun getSampleSentence(request: CreateFlashCardEntryRequest) = request.vocabularyStudyCard.cards
        .flatMap(MagicCard::texts)
        .minByOrNull { it.original.length }

}