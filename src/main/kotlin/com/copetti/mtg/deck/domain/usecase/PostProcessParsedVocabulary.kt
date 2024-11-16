package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.ParsedVocabulary
import org.springframework.stereotype.Service

@Service
class PostProcessParsedVocabulary {

    fun process(vocabularies: List<ParsedVocabulary>): Set<ParsedVocabulary> {
        return vocabularies
            .filter(this::containsKanji)
            .filterNot(this::combinationOfColors)
            .toSet()
    }


    private fun containsKanji(vocabulary: ParsedVocabulary) =
        vocabulary.baseForm.any { c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS }

    private fun combinationOfColors(vocabulary: ParsedVocabulary) =
        vocabulary.baseForm.length > 1 && vocabulary.baseForm.all { c -> c in COLORS }

    companion object {
        private const val COLORS = "白青黒赤緑"
    }
}
