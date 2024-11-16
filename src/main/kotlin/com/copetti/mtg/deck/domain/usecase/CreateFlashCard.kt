package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.common.extensions.getLogger
import com.copetti.mtg.deck.domain.model.FlashCard
import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.MagicCardFace
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import org.springframework.stereotype.Service


data class CreateFlashCardEntryRequest(
    val vocabularyStudyCard: VocabularyStudyCard
)

@Service
class CreateFlashCard(
    private val buildFrequencyInformation: BuildFrequencyInformation
) {

    private val log = getLogger()

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
        appendDefinitions(request, result)
        result.appendLine()
        result.appendLine(buildFrequencyInformation.build(request))
        result.appendLine()

        getSampleSentence(request)?.let {
            result.appendLine(it.text.translation)
            result.appendLine(it.text.original)
        }
        return result.toString()
    }

    private fun appendDefinitions(request: CreateFlashCardEntryRequest, result: StringBuilder) = request
        .vocabularyStudyCard.definition.definitions
        .take(5)
        .forEach(result::appendLine)

    private fun getSampleSentence(request: CreateFlashCardEntryRequest): MagicCardFace? {
        val sampleSentence = request.vocabularyStudyCard.cards
            .flatMap(MagicCard::cardFaces)
            .filter { cardFace -> cardFaceContainsTargetVocabulary(request, cardFace) }
            .minByOrNull { it.text.translation.length }

        if (sampleSentence == null) {
            log.warn("Flash card entry is missing sample sentence | vocabulary: ${request.vocabularyStudyCard.vocabulary}, #cards: ${request.vocabularyStudyCard.cards.size}")
        }

        return sampleSentence
    }

    private fun cardFaceContainsTargetVocabulary(request: CreateFlashCardEntryRequest, cardFace: MagicCardFace) =
        request.vocabularyStudyCard.variations.any { variation -> cardFace.text.translation.contains(variation) }

    private fun buildTags(request: CreateFlashCardEntryRequest) = request.vocabularyStudyCard.cards
        .map(MagicCard::set)
        .distinct()
        .map { set -> "set:$set" }
        .toSet()

}