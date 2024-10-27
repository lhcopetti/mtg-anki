package com.copetti.mtganki.gateway

import com.copetti.mtganki.domain.FlashCard

data class CreateDeckProviderRequest(
    val filePath: String,
    val flashCards: List<FlashCard>
)

interface CreateDeckProvider {

    fun create(request: CreateDeckProviderRequest)
}