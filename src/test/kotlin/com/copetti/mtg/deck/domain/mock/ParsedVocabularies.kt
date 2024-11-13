package com.copetti.mtg.deck.domain.mock

import com.copetti.mtg.deck.domain.model.ParsedVocabulary

object ParsedVocabularies {

    fun given(
        vocabulary: String = "the-vocabulary",
        baseForm: String = vocabulary
    ) = ParsedVocabulary(
        vocabulary = vocabulary,
        baseForm = baseForm
    )
}