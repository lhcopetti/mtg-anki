package com.copetti.mtg.deck.domain.model

import java.time.LocalDate

data class MagicSet (
    val code: String,
    val name: String,
    val releaseDate: LocalDate
)