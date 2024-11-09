package com.copetti.mtganki.domain.usecase

import com.copetti.mtganki.domain.mock.FlashCards
import com.copetti.mtganki.domain.mock.MagicCards
import com.copetti.mtganki.domain.mock.VocabularyStudyCards
import com.copetti.mtganki.gateway.CreateDeckProviderRequest
import com.copetti.mtganki.gateway.LoadMagicCardsExportProvider
import com.copetti.mtganki.provider.anki.AnkiCreateDeckProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BuildMtgAnkiDeckTest {

    @MockK
    private lateinit var loadMagicCardsExportProvider: LoadMagicCardsExportProvider
    @MockK
    private lateinit var processMagicCards: ProcessMagicCards
    @MockK
    private lateinit var createFlashCard: CreateFlashCard
    @MockK
    private lateinit var createDeckProvider: AnkiCreateDeckProvider

    @InjectMockKs
    private lateinit var buildMtgAnkiDeck: BuildMtgAnkiDeck

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

        every { loadMagicCardsExportProvider.loadAll(any()) } returns loadedCards
        every { processMagicCards.process(any()) } returns vocabularyStudyCards

        val firstFlashCardRequest = CreateFlashCardEntryRequest(firstVocabularyCard)
        every { createFlashCard.create(firstFlashCardRequest) } returns firstFlashCard

        val secondFlashCardRequest = CreateFlashCardEntryRequest(secondVocabularyCard)
        every { createFlashCard.create(secondFlashCardRequest) } returns secondFlashCard

        every { createDeckProvider.create(any()) } returns Unit

        buildMtgAnkiDeck.buildDeck(inputFilePath, outputFilePath)

        verify { loadMagicCardsExportProvider.loadAll(inputFilePath) }
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