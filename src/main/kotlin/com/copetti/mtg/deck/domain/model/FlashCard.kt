package com.copetti.mtg.deck.domain.model

data class FlashCard(
    val front: String,
    val back: String,
    val tags: Set<String>
)

