package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.domain.mock.MagicSets
import com.copetti.mtg.deck.domain.mock.VocabularyDefinitions
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import com.copetti.mtg.deck.gateway.DictionaryProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreateMagicStudyCardTest {

    @MockK
    private lateinit var dictionaryProvider: DictionaryProvider

    @InjectMockKs
    private lateinit var createMagicStudyCard: CreateMagicStudyCard

    @Test
    fun `should create a magic study card`() {
        val vocabulary = "the-vocab"
        val set1 = MagicSets.givenMagicSet(code = "set-#1")
        val set2 = MagicSets.givenMagicSet(code = "set-#2")
        val set3 = MagicSets.givenMagicSet(code = "set-#3")

        val cards = setOf(
            MagicCards.givenSingleFacedCard(set = "set-#1"),
            MagicCards.givenSingleFacedCard(set = "set-#3"),
        )
        val sets = listOf( set1, set2, set3 )
        val vocabularyDefinition = VocabularyDefinitions.givenVocabularyDefinition()

        every { dictionaryProvider.lookup(any())} returns vocabularyDefinition

        val request = CreateMagicStudyCardRequest(
            vocabulary = vocabulary,
            relatedCards = cards,
            sets = sets
        )
        val actual = createMagicStudyCard.create(request)
        val expected = VocabularyStudyCard(
            vocabulary = vocabulary,
            definition = vocabularyDefinition,
            cards = request.relatedCards,
            sets = setOf(set1, set3)
        )

        assertThat(actual).isEqualTo(expected)
        verify { dictionaryProvider.lookup(request.vocabulary) }
    }

}