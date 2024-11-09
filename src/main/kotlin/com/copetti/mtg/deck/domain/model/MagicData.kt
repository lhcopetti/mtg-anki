package com.copetti.mtg.deck.domain.model

data class MagicData(
    val cards: List<MagicCard>,
    val sets: List<MagicSet>
)