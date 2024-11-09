package com.copetti.mtg.deck.gateway

data class VocabularyDefinition(
    val reading: String,
    val definitions: List<String>
)

interface DictionaryProvider {

    fun lookup(vocabulary: String): VocabularyDefinition?
}