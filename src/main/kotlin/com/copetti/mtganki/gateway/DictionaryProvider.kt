package com.copetti.mtganki.gateway

data class VocabularyDefinition(
    val vocabulary: String,
    val reading: String,
    val definitions: List<String>
)

interface DictionaryProvider {

    fun lookup(vocabulary: String): VocabularyDefinition?
}