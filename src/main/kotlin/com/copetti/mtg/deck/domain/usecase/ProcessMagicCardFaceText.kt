package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.MagicCardFace
import org.springframework.stereotype.Component

@Component
class ProcessMagicCardFaceText {

    fun process(magicCardFace: MagicCardFace): String {
        val nameRemoved = removeCardName(magicCardFace)
        return removeInvalidTextFromEnchantmentRooms(nameRemoved, magicCardFace)
    }

    private fun removeCardName(magicCardFace: MagicCardFace) =
        magicCardFace.text.translation.replace(magicCardFace.name.translation, "")

    /**
     *
     * This is aimed at the new enchantment room cards that have malformed text such as:
     *
     * Meat Locker // Drowned Diner
     * 冠（かん）水（すい）した食（しょく）堂（どう）\no3oUoU\nあなたがこのドアを開放したとき、カード３枚を引き、その後、カード１枚を捨てる。
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     */
    private fun removeInvalidTextFromEnchantmentRooms(input: String, magicCardFace: MagicCardFace): String {
        /** Transforms "{B}" into "oB" or {3}{U}{U} into "o3oUoU" **/
        val manaCost = magicCardFace.manaCost.replace("{", "o").replace("}", "")
        val manaCostRemovalRegex = "^.*\\n$manaCost\\n".toRegex()
        return manaCostRemovalRegex.replace(input, "")
    }

}