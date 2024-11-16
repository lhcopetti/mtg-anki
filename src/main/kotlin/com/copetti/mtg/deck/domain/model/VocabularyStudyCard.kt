package com.copetti.mtg.deck.domain.model

import com.copetti.mtg.deck.gateway.VocabularyDefinition


data class VocabularyStudyCard (
    val vocabulary: String,
    val variations: Set<String>,
    val definition: VocabularyDefinition,
    val cards: Set<MagicCard>,
    val sets: Set<MagicSet>,
)