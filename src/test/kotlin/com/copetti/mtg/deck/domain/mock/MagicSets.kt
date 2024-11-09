package com.copetti.mtg.deck.domain.mock

import com.copetti.mtg.deck.domain.model.MagicSet
import java.time.LocalDate

object MagicSets {

    fun givenMagicSet(
        code: String = "the-code",
        name: String = "the-name",
        releaseDate: LocalDate = LocalDate.of(2024, 1, 1)
    ) = MagicSet(
        code = code,
        name = name,
        releaseDate = releaseDate
    )
}