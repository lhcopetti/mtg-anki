package com.copetti.mtganki.provider.jisho

import com.copetti.mtganki.gateway.DictionaryProvider
import com.copetti.mtganki.gateway.VocabularyDefinition
import org.springframework.stereotype.Component

@Component
class JishoDictionaryProvider(
    private val jishoClient: JishoClient
): DictionaryProvider {
    override fun lookup(vocabulary: String): VocabularyDefinition? {

       val response = jishoClient.searchWord(vocabulary)
        val reading = getReading(response)
        val definitions = getDefinitions(response)
        return VocabularyDefinition(vocabulary, reading, definitions)
    }

    private fun getReading(response: JishoSearchResponse): String {
        return response.data.firstOrNull()?.japanese?.firstOrNull()?.reading ?: "<reading not found>"
    }

    private fun getDefinitions(response: JishoSearchResponse): List<String> {
        return response.data.firstOrNull()?.senses
            ?.map { sense -> sense.englishDefinitions.joinToString(separator = ", ")}
            .orEmpty()
    }

}