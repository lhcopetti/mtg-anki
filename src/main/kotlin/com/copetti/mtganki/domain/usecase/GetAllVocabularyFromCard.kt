package com.copetti.mtganki.domain.usecase

import com.copetti.mtganki.domain.model.DualLanguageText
import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.gateway.JapaneseParserProvider
import org.springframework.stereotype.Service
import java.lang.Character.UnicodeBlock

@Service
class GetAllVocabularyFromCard(
    private val japaneseParserProvider: JapaneseParserProvider
) {

    fun getVocabulary(magicCard: MagicCard): Set<String> {
        val allText = getAllTextFromCard(magicCard)
        val parsed = japaneseParserProvider.parse(allText).toSet()
        return parsed.filter(this::containsKanji).toSet()
    }

    private fun getAllTextFromCard(magicCard: MagicCard): String {
        var allText = magicCard.texts.map(DualLanguageText::translation).joinToString(separator = " ")
        magicCard.names.forEach { allText = allText.replace(it.translation, "") }
        return allText
    }

    private fun containsKanji(vocabulary: String) =
        vocabulary.any { c -> UnicodeBlock.of(c) == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS }

}