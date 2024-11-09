package com.copetti.mtganki.domain.mock

import com.copetti.mtganki.domain.model.VocabularyStudyCard
import com.copetti.mtganki.gateway.VocabularyDefinition

object VocabularyStudyCards {

    fun givenVocabularyStudyCard() = VocabularyStudyCard(
        vocabulary = "the-vocabulary",
        definition = VocabularyDefinition(
            reading = "the-reading",
            definitions = listOf(
                "the-first-definition",
                "the-second-definition",
            )
        ),
        cards = setOf(
            MagicCards.givenSingleFacedCard()
        )
    )
}