package com.copetti.mtg.deck.domain.mock

import com.copetti.mtg.deck.domain.model.FlashCard


object FlashCards {

    fun givenFlashCard(
        front: String = "the-front",
        back: String = "the-back",
        tags: Set<String> = setOf("tag-1", "tag-2")
    ) = FlashCard(
        front = front,
        back = back,
        tags = tags
    )
}