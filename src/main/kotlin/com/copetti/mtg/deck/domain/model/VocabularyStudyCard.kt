package com.copetti.mtg.deck.domain.model

import com.copetti.mtg.deck.gateway.VocabularyDefinition


data class VocabularyStudyCard (
    val vocabulary: String,
    val definition: VocabularyDefinition,
    val cards: Set<MagicCard>,
)