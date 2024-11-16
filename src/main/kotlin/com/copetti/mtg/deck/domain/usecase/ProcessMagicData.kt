package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.MagicData
import com.copetti.mtg.deck.domain.model.ParsedVocabulary
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import org.springframework.stereotype.Component

data class VocabularyEntry(
    val variations: MutableSet<String> = mutableSetOf(),
    val cards: MutableList<MagicCard> = mutableListOf()
)

@Component
class ProcessMagicData(
    private val getAllVocabularyFromCard: GetAllVocabularyFromCard,
    private val createMagicStudyCard: CreateMagicStudyCard,
) {

    fun process(magicData: MagicData): Set<VocabularyStudyCard> {

        return buildVocabularyCollection(magicData.cards)
            .map { (vocab, cards) -> createMagicStudyCard(magicData, vocab, cards) }
            .filterNotNull()
            .toSet()
    }

    private fun buildVocabularyCollection(magicCards: List<MagicCard>): Map<String, VocabularyEntry> {
        val vocabularyCollection = mutableMapOf<String, VocabularyEntry>()

        magicCards.forEach { magicCard ->
            val vocabularyList = getAllVocabularyFromCard.getVocabulary(magicCard)
            vocabularyList.forEach { parsedVocabulary ->
                linkMagicCardToVocabulary(vocabularyCollection, magicCard, parsedVocabulary)
            }
        }

        return vocabularyCollection
    }

    private fun linkMagicCardToVocabulary(
        vocabularyCollection: MutableMap<String, VocabularyEntry>,
        magicCard: MagicCard,
        parsedVocabulary: ParsedVocabulary
    ) {
        val entry = vocabularyCollection.getOrPut(parsedVocabulary.baseForm) { VocabularyEntry() }
        entry.cards.add(magicCard)
        entry.variations.add(parsedVocabulary.vocabulary)
    }

    private fun createMagicStudyCard(
        magicData: MagicData,
        vocabulary: String,
        vocabularyEntry: VocabularyEntry
    ): VocabularyStudyCard? {
        val request = CreateMagicStudyCardRequest(
            vocabulary = vocabulary,
            variations = vocabularyEntry.variations,
            relatedCards = vocabularyEntry.cards.toSet(),
            sets = magicData.sets,
        )
        return createMagicStudyCard.create(request)
    }

}