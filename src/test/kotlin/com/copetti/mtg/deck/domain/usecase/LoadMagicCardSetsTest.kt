package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.domain.mock.MagicSets
import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.MagicSet
import com.copetti.mtg.deck.gateway.MagicSetDataProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoadMagicCardSetsTest {

    @MockK
    private lateinit var magicSetDataProvider: MagicSetDataProvider

    @InjectMockKs
    private lateinit var loadMagicCardSets: LoadMagicCardSets

    @Test
    fun `should load set data for the sets from the cards`() {
        val card = MagicCards.givenSingleFacedCard()
        val cards = listOf(card)

        val set = MagicSets.givenMagicSet()
        every { magicSetDataProvider.retrieveSet(any()) } returns set

        val actual = loadMagicCardSets.load(cards)

        assertThat(actual).isEqualTo(listOf(set))
        verify { magicSetDataProvider.retrieveSet(card.set) }
    }

    @Test
    fun `should load the set data for each unique set only once`() {
        val setCode = "the-set-code"
        val cards = listOf(
            MagicCards.givenSingleFacedCard(set = setCode),
            MagicCards.givenSingleFacedCard(set = setCode),
            MagicCards.givenSingleFacedCard(set = setCode),
            MagicCards.givenSingleFacedCard(set = setCode),
        )

        val set = MagicSets.givenMagicSet()
        every { magicSetDataProvider.retrieveSet(any()) } returns set

        val actual = loadMagicCardSets.load(cards)

        assertThat(actual).isEqualTo(listOf(set))
        verify(exactly = 1) { magicSetDataProvider.retrieveSet(setCode) }
    }

    @Test
    fun `should load the set data for all sets from cards`() {
        val cards = mutableListOf<MagicCard>()
        val sets = mutableListOf<MagicSet>()


        for (i in 1..10) {
            val setCode = "set-#$i"
            cards.add(MagicCards.givenSingleFacedCard(set = setCode))
            val set = MagicSets.givenMagicSet(code = setCode)
            sets.add(set)

            every { magicSetDataProvider.retrieveSet(setCode) } returns set
        }

        val actual = loadMagicCardSets.load(cards)

        assertThat(actual).isEqualTo(sets)

        for (i in 1..10) {
            verify { magicSetDataProvider.retrieveSet("set-#$i") }
        }
    }
}