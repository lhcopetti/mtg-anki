package com.copetti.mtg.deck.domain.mock

import com.copetti.mtg.deck.domain.model.MagicSet
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard

object VocabularyStudyCards {

    fun givenVocabularyStudyCard(
        sets: Set<MagicSet> = setOf(MagicSets.givenMagicSet())
    ) = VocabularyStudyCard(
        vocabulary = "the-vocabulary",
        definition = VocabularyDefinitions.givenVocabularyDefinition(),
        cards = setOf(
            MagicCards.givenSingleFacedCard()
        ),
        sets = sets
    )
}