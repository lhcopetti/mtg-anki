package com.copetti.mtganki.domain.processor

import com.copetti.mtganki.domain.card.DualLanguageText
import com.copetti.mtganki.domain.card.MagicCard
import org.springframework.stereotype.Component

@Component
class MagicCardProcessor {

    fun process(magicCards: List<MagicCard>): String {
        return magicCards.stream()
            .filter { it.lang == "ja" && it.set == "dsk" }
            .map (this::getAllTextWithoutCardNames)
            .toList()
            .joinToString(separator = "\n")
    }

    private fun getAllTextWithoutCardNames(magicCard: MagicCard): String {
        var allText = magicCard.texts.map (DualLanguageText::translation).joinToString(separator = " ")
        magicCard.names.forEach { allText = allText.replace(it.translation, "") }
        return allText
    }
}