package com.copetti.mtg.deck.provider.scryfall.model

import com.copetti.mtg.deck.domain.model.Legality
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class ScryfallLegalityTest {

    @ParameterizedTest
    @EnumSource(ScryfallLegality::class)
    fun `should map legalities to domain correctly`(scryfallLegality: ScryfallLegality) {
        val expected = Legality.valueOf(scryfallLegality.name)
        val actual = scryfallLegality.toDomain()

        assertThat(actual).isEqualTo(expected)
    }
}