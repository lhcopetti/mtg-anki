package com.copetti.mtganki.domain.usecase

import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.gateway.JapaneseParserProvider
import org.springframework.stereotype.Service

@Service
class GetAllVocabularyFromCard(
    private val japaneseParserProvider: JapaneseParserProvider,
    private val processMagicCardFaceText: ProcessMagicCardFaceText,
    private val processParsedVocabulary: ProcessParsedVocabulary
) {

    fun getVocabulary(magicCard: MagicCard): Set<String> {
        val allText = getAllTextFromCard(magicCard)
        val parsed = japaneseParserProvider.parse(allText).toSet()
        return processParsedVocabulary.process(parsed)
    }

    private fun getAllTextFromCard(magicCard: MagicCard): String {
        return magicCard.cardFaces
            .map(processMagicCardFaceText::process)
            .joinToString(separator = " ")
    }

}