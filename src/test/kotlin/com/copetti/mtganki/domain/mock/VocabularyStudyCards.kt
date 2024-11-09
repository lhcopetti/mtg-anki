package com.copetti.mtganki.domain.mock

import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import com.copetti.mtg.deck.gateway.VocabularyDefinition

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