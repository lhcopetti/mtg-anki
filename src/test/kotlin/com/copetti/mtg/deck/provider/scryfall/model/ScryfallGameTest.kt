package com.copetti.mtg.deck.provider.scryfall.model

import com.copetti.mtg.deck.domain.model.GameLegality
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScryfallGameTest {

    @Test
    fun `should convert mtgo value correctly`() {
        val actual = ScryfallGame.MTGO.toDomain()
        val expected = GameLegality.MTG_ONLINE
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should convert arena value correctly`() {
        val actual = ScryfallGame.ARENA.toDomain()
        val expected = GameLegality.MAGIC_ARENA
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should convert paper value correctly`() {
        val actual = ScryfallGame.PAPER.toDomain()
        val expected = GameLegality.PAPER
        assertThat(actual).isEqualTo(expected)
    }
}