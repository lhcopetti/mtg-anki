package com.copetti.mtg.deck.domain.mock

import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.MagicSet
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import com.copetti.mtg.deck.gateway.VocabularyDefinition

object VocabularyStudyCards {

    fun givenVocabularyStudyCard(
        vocabulary: String = "the-vocabulary",
        variations: Set<String> = setOf(vocabulary),
        definition: VocabularyDefinition = VocabularyDefinitions.givenVocabularyDefinition(),
        cards: Set<MagicCard> = setOf(MagicCards.givenSingleFacedCard()),
        sets: Set<MagicSet> = setOf(MagicSets.givenMagicSet()),
    ) = VocabularyStudyCard(
        vocabulary = vocabulary,
        variations = variations,
        definition = definition,
        cards = cards,
        sets = sets
    )
}