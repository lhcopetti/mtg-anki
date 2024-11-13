package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.ParsedVocabulary
import org.springframework.stereotype.Service

@Service
class PostProcessParsedVocabulary {

    fun process(vocabularies: List<ParsedVocabulary>): Set<String> {
        return vocabularies
            .map(ParsedVocabulary::baseForm)
            .filter(this::containsKanji)
            .filterNot(this::combinationOfColors)
            .toSet()
    }

    private fun combinationOfColors(vocabulary: String) = vocabulary.length > 1 && vocabulary.all { c -> c in COLORS }

    private fun containsKanji(vocabulary: String) =
        vocabulary.any { c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS }

    companion object {
        private const val COLORS = "白青黒赤緑"
    }
}
