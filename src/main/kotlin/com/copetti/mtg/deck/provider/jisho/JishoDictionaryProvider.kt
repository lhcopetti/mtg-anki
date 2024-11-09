package com.copetti.mtg.deck.provider.jisho

import com.copetti.mtg.deck.common.extensions.getLogger
import com.copetti.mtg.deck.gateway.DictionaryProvider
import com.copetti.mtg.deck.gateway.VocabularyDefinition
import org.springframework.stereotype.Component


@Component
class JishoDictionaryProvider(
    private val jishoClient: JishoClient
) : DictionaryProvider {

    private val log = getLogger()

    override fun lookup(vocabulary: String): VocabularyDefinition? {

        try {
            val response = jishoClient.searchWord(vocabulary)

            if (response.data.isEmpty()) {
                log.info("No data returned from jisho | vocabulary: $vocabulary")
                return null
            }

            if (response.data.firstOrNull()?.slug != vocabulary) {
                log.warn("Searched vocabulary does not match slug. Vocabulary: $vocabulary, slug: ${response.data.firstOrNull()?.slug}")
            }

            val reading = getReading(response)
            val definitions = getDefinitions(response)
            return VocabularyDefinition(reading, definitions)
        } catch (ex: Exception) {
            log.error("Error occurred during retrieval of dictionary entry for vocabulary: $vocabulary", ex)
            throw ex
        }
    }

    private fun getReading(response: JishoSearchResponse): String {
        return response.data.firstOrNull()?.japanese?.firstOrNull()?.reading ?: "<reading not found>"
    }

    private fun getDefinitions(response: JishoSearchResponse): List<String> {
        return response.data.firstOrNull()?.senses
            ?.map { sense -> sense.englishDefinitions.joinToString(separator = ", ") }
            .orEmpty()
    }

}