package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.MagicSet
import org.springframework.stereotype.Component

@Component
class BuildMagicSetInformation {

    fun build(request: CreateFlashCardEntryRequest): String {
        val sets = request.vocabularyStudyCard.sets
        val highlightedSets = sets.sortedByDescending(MagicSet::releaseDate).take(3)
        val highlightedSetLine = highlightedSets.joinToString(", ") { set -> "\"${set.name}\"" }
        val remainingSetCount =
            if (sets.size > HIGHLIGHTED_SET_COUNT) " and ${sets.size - HIGHLIGHTED_SET_COUNT} more" else ""
        val setText = if (sets.size > 1) "sets" else "set"
        return "Present in ${setText}: ${highlightedSetLine}${remainingSetCount}."
    }

    companion object {
        private const val HIGHLIGHTED_SET_COUNT = 3
    }
}