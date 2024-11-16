package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.MagicSet
import org.springframework.stereotype.Component

@Component
class BuildFrequencyInformation {

    fun build(request: CreateFlashCardEntryRequest): String {
        val result = StringBuilder()
        result.appendLine(buildFrequencyText(request))
        result.append(buildSetText(request))
        return result.toString()
    }

    private fun buildFrequencyText(request: CreateFlashCardEntryRequest): String {
        val count = request.vocabularyStudyCard.cards.size
        return "Appears $count ${pluralize("time", count)}."
    }

    private fun buildSetText(request: CreateFlashCardEntryRequest): String {
        val sets = request.vocabularyStudyCard.sets
        val highlightedSets = sets.sortedByDescending(MagicSet::releaseDate).take(3)
        val highlightedSetLine = highlightedSets.joinToString(", ") { set -> buildSetEntry(request, set) }
        val remainingSetCount =
            if (sets.size > HIGHLIGHTED_SET_COUNT) " and ${sets.size - HIGHLIGHTED_SET_COUNT} more" else ""
        return "Present in ${pluralize("set", sets.size)}: ${highlightedSetLine}${remainingSetCount}."
    }

    private fun buildSetEntry(request: CreateFlashCardEntryRequest, set: MagicSet): String {
        val count = request.vocabularyStudyCard.cards.filter { card -> card.set == set.code }.size
        return "\"${set.name}\" (x$count)"
    }

    private fun pluralize(word: String, count: Int) = if (count > 1) "${word}s" else word

    companion object {
        private const val HIGHLIGHTED_SET_COUNT = 3
    }
}