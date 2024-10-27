package com.copetti.mtganki.gateway

data class VocabularyDefinition(
    val reading: String,
    val definitions: List<String>
)

interface DictionaryProvider {

    fun lookup(vocabulary: String): VocabularyDefinition?
}