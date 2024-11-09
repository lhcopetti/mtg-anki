package com.copetti.mtganki.domain.usecase

import com.copetti.mtg.deck.domain.usecase.ProcessMagicCardFaceText
import com.copetti.mtganki.domain.mock.MagicCardFaces
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ProcessMagicCardFaceTextTest {

    @InjectMockKs
    private lateinit var processMagicCardFaceText: ProcessMagicCardFaceText

    @Test
    fun `should remove the card name translation from the magic card face`() {
        val enchantmentRoomCard = MagicCardFaces.givenMagicCardFaces(
            translationName = "永劫の無垢",
            cardText = "Lifelink\\nWhenever one or more other creatures...",
            translationCardText = "絆魂\n...\n永劫の無垢が死亡したとき...",
        )

        val actual = processMagicCardFaceText.process(enchantmentRoomCard)
        val expected = "絆魂\n...\nが死亡したとき..."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should remove invalid starting lines from the second card face of enchantment rooms`() {
        val enchantmentRoomCard = MagicCardFaces.givenMagicCardFaces(
            manaCost = "{B}",
            translationCardText = "解（かい）剖（ぼう）室（しつ）\noB\nあなたがこのドアを開放したとき..."
        )

        val actual = processMagicCardFaceText.process(enchantmentRoomCard)
        val expected = "あなたがこのドアを開放したとき..."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should remove invalid starting lines from the second card face of enchantment rooms multicolored`() {
        val enchantmentRoomCard = MagicCardFaces.givenMagicCardFaces(
            manaCost = "{3}{U}{U}",
            translationCardText = "冠（かん）水（すい）した食（しょく）堂（どう）\no3oUoU\nあなたがこのドアを開放したとき、カード３枚を引き、その後、カード１枚を捨てる。"
        )

        val actual = processMagicCardFaceText.process(enchantmentRoomCard)
        val expected = "あなたがこのドアを開放したとき、カード３枚を引き、その後、カード１枚を捨てる。"

        assertThat(actual).isEqualTo(expected)
    }
}