package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.MagicCardFace
import org.springframework.stereotype.Component

@Component
class ProcessMagicCardFaceText {

    fun process(magicCard: MagicCard): String {
        return magicCard.cardFaces
            .map(this::processMagicCardFaceText)
            .map { text -> removeCardNames(magicCard, text) }
            .joinToString(separator = " ")
    }

    private fun removeCardNames(magicCard: MagicCard, text: String): String = magicCard.cardFaces
        .map { face -> face.name.translation }
        .fold(text) { acc, value -> acc.replace(value, "") }

    private fun processMagicCardFaceText(magicCardFace: MagicCardFace) =
        removeInvalidTextFromEnchantmentRooms(magicCardFace)

    /**
     *
     * This is aimed at the new enchantment room cards that have malformed text such as:
     *
     * Meat Locker // Drowned Diner
     * 冠（かん）水（すい）した食（しょく）堂（どう）\no3oUoU\nあなたがこのドアを開放したとき、カード３枚を引き、その後、カード１枚を捨てる。
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     */
    private fun removeInvalidTextFromEnchantmentRooms(magicCardFace: MagicCardFace): String {
        /** Transforms "{B}" into "oB" or {3}{U}{U} into "o3oUoU" **/
        val manaCost = magicCardFace.manaCost.replace("{", "o").replace("}", "")
        val manaCostRemovalRegex = "^.*\\n$manaCost\\n".toRegex()
        return manaCostRemovalRegex.replace(magicCardFace.text.translation, "")
    }

}