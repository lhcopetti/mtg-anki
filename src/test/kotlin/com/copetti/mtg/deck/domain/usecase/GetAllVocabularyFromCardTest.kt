package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.gateway.JapaneseParserProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GetAllVocabularyFromCardTest {

    @InjectMockKs
    private lateinit var getAllVocabularyFromCard: GetAllVocabularyFromCard

    @MockK
    private lateinit var processMagicCardFaceText: ProcessMagicCardFaceText

    @MockK
    private lateinit var processParsedVocabulary: ProcessParsedVocabulary

    @MockK
    private lateinit var japaneseParserProvider: JapaneseParserProvider


    @Test
    fun `should process single magic card correctly`() {
        val magicCard = MagicCards.givenSingleFacedCard()

        every { processMagicCardFaceText.process(any()) } returns "text extracted"
        every { japaneseParserProvider.parse(any()) } returns listOf("parsed", "text")
        every { processParsedVocabulary.process(any()) } returns setOf("processed", "vocabulary")

        val actual = getAllVocabularyFromCard.getVocabulary(magicCard)
        val expected = setOf("processed", "vocabulary")

        assertThat(actual).isEqualTo(expected)

        verify { processMagicCardFaceText.process(magicCard) }
        verify { japaneseParserProvider.parse("text extracted") }
        verify { processParsedVocabulary.process(setOf("parsed", "text")) }
    }

    @Test
    fun `should process multi faced cards correctly`() {
        val multiFacedMagicCard = MagicCards.givenMultiFacedCard()

        every { processMagicCardFaceText.process(any()) } returns "first card text second card text"
        every { japaneseParserProvider.parse(any()) } returns listOf("parsed", "text")
        every { processParsedVocabulary.process(any()) } returns setOf("processed", "vocabulary")

        val actual = getAllVocabularyFromCard.getVocabulary(multiFacedMagicCard)
        val expected = setOf("processed", "vocabulary")

        assertThat(actual).isEqualTo(expected)


        verify { processMagicCardFaceText.process(multiFacedMagicCard) }

        val expectedParserInput = "first card text second card text"
        verify { japaneseParserProvider.parse(expectedParserInput) }

        val expectedProcessInput = setOf("parsed", "text")
        verify { processParsedVocabulary.process(expectedProcessInput) }
    }

}