package com.copetti.mtg.deck.provider.scryfall.model

import com.copetti.mtg.deck.domain.model.GameLegality
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class ScryfallGameTest {

    @ParameterizedTest
    @EnumSource(ScryfallGame::class)
    fun `should map games to domain correctly`(scryfallGame: ScryfallGame) {
        val expected = GameLegality.valueOf(scryfallGame.name)
        val actual = scryfallGame.toDomain()

        assertThat(actual).isEqualTo(expected)
    }
}