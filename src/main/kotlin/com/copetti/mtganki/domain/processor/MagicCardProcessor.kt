package com.copetti.mtganki.domain.processor

import com.copetti.mtganki.domain.card.MagicCard
import com.copetti.mtganki.domain.card.VocabularyStudyCard
import org.springframework.stereotype.Component

@Component
class MagicCardProcessor(
    private val getAllVocabularyFromCard: GetAllVocabularyFromCard,
    private val createMagicStudyCard: CreateMagicStudyCard
) {

    fun process(magicCards: List<MagicCard>): Set<VocabularyStudyCard> {

        val targetCards = magicCards.filter { it.lang == "ja" && it.set == "dsk" }
        return buildVocabularyCollection(targetCards)
            .map { (vocab, cards) -> createMagicStudyCard.create(vocab, cards) }
            .filterNotNull()
            .toSet()
    }

    private fun buildVocabularyCollection(magicCards: List<MagicCard>): Map<String, Set<MagicCard>> {
        val vocabularyCollection = mutableMapOf<String, MutableSet<MagicCard>>()

        magicCards.forEach { magicCard ->
            val vocabularyList = getAllVocabularyFromCard.getVocabulary(magicCard)
            addAllVocabulary(vocabularyCollection, magicCard, vocabularyList)
        }

        return vocabularyCollection
    }

    private fun addAllVocabulary(
        vocabularyCollection: MutableMap<String, MutableSet<MagicCard>>,
        magicCard: MagicCard,
        vocabularyList: Set<String>
    ) {
        vocabularyList.stream().forEach { vocab ->
            val cards = vocabularyCollection.getOrPut(vocab) { mutableSetOf() }
            cards.add(magicCard)
        }
    }
}