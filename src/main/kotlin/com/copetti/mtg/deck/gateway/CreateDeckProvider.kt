package com.copetti.mtg.deck.gateway

import com.copetti.mtg.deck.domain.model.FlashCard


data class CreateDeckProviderRequest(
    val filePath: String,
    val flashCards: List<FlashCard>
)

interface CreateDeckProvider {

    fun create(request: CreateDeckProviderRequest)
}