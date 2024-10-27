package com.copetti.mtganki

import com.copetti.mtganki.domain.model.FlashCard
import com.copetti.mtganki.domain.model.VocabularyStudyCard
import com.copetti.mtganki.domain.usecase.CreateFlashCard
import com.copetti.mtganki.domain.usecase.CreateFlashCardEntryRequest
import com.copetti.mtganki.domain.usecase.ProcessMagicCards
import com.copetti.mtganki.gateway.CreateDeckProvider
import com.copetti.mtganki.gateway.CreateDeckProviderRequest
import com.copetti.mtganki.provider.scryfall.ScryfallMagicMapper
import org.springframework.stereotype.Component

@Component
class MtgAnkiDeckBuilder(
    val scryfallMagicCardFileReader: ScryfallMagicCardFileReader,
    val scryfallMagicMapper: ScryfallMagicMapper,
    val processMagicCards: ProcessMagicCards,
    val createFlashCard: CreateFlashCard,
    val createDeckProvider: CreateDeckProvider
) {

    fun buildDeck(inputFilePath: String, exportFilePath: String) {
        val scryfallMagicCards = scryfallMagicCardFileReader.loadCards(inputFilePath)
        val magicCards = scryfallMagicCards.map(scryfallMagicMapper::toMagicCard)
        val studyCards = processMagicCards.process(magicCards)
        val flashCardEntries = createFlashCards(studyCards)
        createDeckProvider.create(CreateDeckProviderRequest(exportFilePath, flashCardEntries))
    }

    private fun createFlashCards(studyCard: Set<VocabularyStudyCard>): List<FlashCard> {
        return studyCard.map(::CreateFlashCardEntryRequest)
            .map(createFlashCard::create)
    }
}