package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.FlashCards
import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.domain.mock.VocabularyStudyCards
import com.copetti.mtg.deck.gateway.CreateDeckProviderRequest
import com.copetti.mtg.deck.gateway.LoadMagicCardsExportProvider
import com.copetti.mtg.deck.provider.anki.AnkiCreateDeckProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BuildMtgDeckTest {

    @MockK
    private lateinit var loadMagicCardsFromExport: LoadMagicCardsFromExport
    @MockK
    private lateinit var processMagicCards: ProcessMagicCards
    @MockK
    private lateinit var createFlashCard: CreateFlashCard
    @MockK
    private lateinit var createDeckProvider: AnkiCreateDeckProvider

    @InjectMockKs
    private lateinit var buildMtgDeck: BuildMtgDeck

    @Test
    fun `should correctly load process and create the deck`() {
        val inputFilePath = "the-input"
        val outputFilePath = "the-output"

        val loadedCards = listOf(
            MagicCards.givenSingleFacedCard(),
            MagicCards.givenMultiFacedCard()
        )
        val firstVocabularyCard = VocabularyStudyCards.givenVocabularyStudyCard()
        val secondVocabularyCard = VocabularyStudyCards.givenVocabularyStudyCard()

        val vocabularyStudyCards = setOf( firstVocabularyCard, secondVocabularyCard)
        val firstFlashCard = FlashCards.givenFlashCard(front = "first")
        val secondFlashCard = FlashCards.givenFlashCard(front = "first")

        every { loadMagicCardsFromExport.load(any()) } returns loadedCards
        every { processMagicCards.process(any()) } returns vocabularyStudyCards

        val firstFlashCardRequest = CreateFlashCardEntryRequest(firstVocabularyCard)
        every { createFlashCard.create(firstFlashCardRequest) } returns firstFlashCard

        val secondFlashCardRequest = CreateFlashCardEntryRequest(secondVocabularyCard)
        every { createFlashCard.create(secondFlashCardRequest) } returns secondFlashCard

        every { createDeckProvider.create(any()) } returns Unit

        buildMtgDeck.buildDeck(inputFilePath, outputFilePath)

        verify { loadMagicCardsFromExport.load(inputFilePath) }
        verify { processMagicCards.process(loadedCards) }
        verify { createFlashCard.create(firstFlashCardRequest) }
        verify { createFlashCard.create(secondFlashCardRequest) }

        val expectedCreateDeckRequest = CreateDeckProviderRequest(
            filePath = outputFilePath,
            flashCards = listOf(firstFlashCard, secondFlashCard)
        )
        verify { createDeckProvider.create(expectedCreateDeckRequest) }
    }
}