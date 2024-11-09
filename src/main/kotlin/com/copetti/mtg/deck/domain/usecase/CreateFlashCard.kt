package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.FlashCard
import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import org.springframework.stereotype.Service


data class CreateFlashCardEntryRequest(
    val vocabularyStudyCard: VocabularyStudyCard
)

@Service
class CreateFlashCard {

    fun create(request: CreateFlashCardEntryRequest): FlashCard {
        val front = buildFront(request)
        val back = buildBack(request)
        val tags = buildTags(request)
        return FlashCard(front = front, back = back, tags = tags)
    }

    private fun buildFront(request: CreateFlashCardEntryRequest) = request.vocabularyStudyCard.vocabulary

    private fun buildBack(request: CreateFlashCardEntryRequest): String {
        val result = StringBuilder()
        result.appendLine(request.vocabularyStudyCard.definition.reading)
        request.vocabularyStudyCard.definition.definitions.forEach(result::appendLine)
        result.appendLine()

        getSampleSentence(request)?.let {
            result.appendLine(it.texts.translation)
            result.appendLine(it.texts.original)
        }
        return result.toString()
    }

    private fun getSampleSentence(request: CreateFlashCardEntryRequest) = request.vocabularyStudyCard.cards
        .flatMap(MagicCard::cardFaces)
        .filter { cardFace -> cardFace.texts.translation.contains(request.vocabularyStudyCard.vocabulary) }
        .minByOrNull { it.texts.translation.length }

    private fun buildTags(request: CreateFlashCardEntryRequest) = request.vocabularyStudyCard.cards
        .map (MagicCard::set)
        .distinct()
        .map { set -> "set:$set" }
        .toSet()

}