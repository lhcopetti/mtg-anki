package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.ParsedVocabulary
import com.copetti.mtg.deck.gateway.JapaneseParserProvider
import org.springframework.stereotype.Service

@Service
class GetAllVocabularyFromCard(
    private val japaneseParserProvider: JapaneseParserProvider,
    private val preProcessMagicCardText: PreProcessMagicCardText,
    private val postProcessParsedVocabulary: PostProcessParsedVocabulary
) {

    fun getVocabulary(magicCard: MagicCard): Set<ParsedVocabulary> {
        val allText = getAllTextFromCard(magicCard)
        val parsed = japaneseParserProvider.parse(allText)
        return postProcessParsedVocabulary.process(parsed)
    }

    private fun getAllTextFromCard(magicCard: MagicCard): String {
        return preProcessMagicCardText.process(magicCard)
    }

}