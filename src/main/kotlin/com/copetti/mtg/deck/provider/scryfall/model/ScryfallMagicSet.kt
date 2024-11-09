package com.copetti.mtg.deck.provider.scryfall.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class ScryfallMagicSet(
    val code: String,
    val name: String,
    @JsonProperty("released_at")
    val releasedAt: LocalDate
)