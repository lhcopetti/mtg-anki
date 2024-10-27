package com.copetti.mtganki.domain.model

import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.gateway.VocabularyDefinition

data class VocabularyStudyCard (
    val vocabulary: String,
    val definition: VocabularyDefinition,
    val cards: Set<MagicCard>,
)