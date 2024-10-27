package com.copetti.mtganki.domain.card

import com.copetti.mtganki.gateway.VocabularyDefinition

data class VocabularyStudyCard (
    val vocabulary: String,
    val definition: VocabularyDefinition,
    val cards: Set<MagicCard>,
)