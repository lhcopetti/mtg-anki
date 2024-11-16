package com.copetti.mtg.deck.provider.scryfall

import com.copetti.mtg.deck.domain.model.MagicSet
import com.copetti.mtg.deck.provider.scryfall.client.ScryfallApiClient
import com.copetti.mtg.deck.provider.scryfall.model.ScryfallMagicSet
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class ScryfallMagicDataProviderTest {

    @MockK
    private lateinit var client: ScryfallApiClient

    @InjectMockKs
    private lateinit var scryfallMagicDataProvider: ScryfallMagicDataProvider

    @Test
    fun `should retrieve magic set data from client correctly`() {
        val code = "the-code"
        val scryfallResponse = ScryfallMagicSet(
            code = code,
            name = "the-name",
            releasedAt = LocalDate.of(2024, 1, 1)
        )

        every { client.retrieveSet(any()) } returns scryfallResponse

        val actual = scryfallMagicDataProvider.retrieveSet(code)
        val expected = MagicSet(
            code = code,
            name = "the-name",
            releaseDate = LocalDate.of(2024, 1, 1)
        )

        assertThat(actual).isEqualTo(expected)

        verify { client.retrieveSet(code) }
    }
}