package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.common.extensions.getLogger
import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.MagicSet
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import com.copetti.mtg.deck.gateway.DictionaryProvider
import org.slf4j.Logger
import org.springframework.stereotype.Service

data class CreateMagicStudyCardRequest(
    val vocabulary: String,
    val variations: Set<String>,
    val relatedCards: Set<MagicCard>,
    val sets: List<MagicSet>,
)

@Service
class CreateMagicStudyCard(
    private val dictionaryProvider: DictionaryProvider
) {

    private val logger: Logger = getLogger()

    fun create(request: CreateMagicStudyCardRequest): VocabularyStudyCard? {

        val definition = dictionaryProvider.lookup(request.vocabulary)

        if (definition == null) {
            logger.info("Vocabulary [${request.vocabulary}] has no definition, will not create a study card")
            return null
        }

        val sets = getSetsContainedInCards(request.sets, request.relatedCards)

        return VocabularyStudyCard(
            vocabulary = request.vocabulary,
            variations = request.variations,
            definition = definition,
            cards = request.relatedCards,
            sets = sets
        )
    }

    private fun getSetsContainedInCards(sets: List<MagicSet>, cards: Set<MagicCard>): Set<MagicSet> {
        val setsFromCards = cards.map(MagicCard::set).toSet()
        return sets.filter { set -> setsFromCards.contains(set.code) }.toSet()
    }
}