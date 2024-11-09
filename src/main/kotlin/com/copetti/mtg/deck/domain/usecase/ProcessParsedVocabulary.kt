package com.copetti.mtg.deck.domain.usecase

import org.springframework.stereotype.Service

@Service
class ProcessParsedVocabulary {

    fun process(vocabularies: Set<String>): Set<String> {
        return vocabularies
            .filter { vocabulary -> containsKanji(vocabulary) }
            .toSet()
    }

    private fun containsKanji(vocabulary: String) =
        vocabulary.any { c -> Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS }
}
