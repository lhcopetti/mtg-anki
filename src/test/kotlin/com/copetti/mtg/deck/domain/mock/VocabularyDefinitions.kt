package com.copetti.mtg.deck.domain.mock

import com.copetti.mtg.deck.gateway.VocabularyDefinition

object VocabularyDefinitions {

    fun givenVocabularyDefinition() = VocabularyDefinition(
        reading = "the-reading",
        definitions = listOf(
            "the-first-definition",
            "the-second-definition",
        )
    )
}