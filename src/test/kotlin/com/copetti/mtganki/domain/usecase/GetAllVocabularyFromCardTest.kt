package com.copetti.mtganki.domain.usecase

import com.copetti.mtganki.domain.model.DualLanguageText
import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.domain.model.MagicCardFace
import com.copetti.mtganki.gateway.JapaneseParserProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class GetAllVocabularyFromCardTest {

    @InjectMockKs
    private lateinit var getAllVocabularyFromCard: GetAllVocabularyFromCard

    @MockK
    private lateinit var japaneseParserProvider: JapaneseParserProvider


    @Test
    fun `should remove card names before parsing`() {
        val enchantmentRoomCard = MagicCard(
            id = UUID.randomUUID().toString(),
            set = "any-set",
            lang = "any-lang",
            cardFaces = listOf(
                MagicCardFace(
                    name = DualLanguageText(original = "Enduring Innocence", translation = "永劫の無垢"),
                    texts = DualLanguageText(
                        original = "Lifelink\nWhenever one or more other creatures...",
                        translation = "絆魂\n...\n永劫の無垢が死亡したとき..."
                    )
                )
            )
        )

        every { japaneseParserProvider.parse(any()) } returns listOf("開放")

        val actual = getAllVocabularyFromCard.getVocabulary(enchantmentRoomCard)
        val expected = setOf("開放")

        assertThat(actual).isEqualTo(expected)

        val expectedParserInput = "絆魂\n...\nが死亡したとき..."
        verify { japaneseParserProvider.parse(expectedParserInput) }
    }

    @Test
    fun `should remove invalid starting lines from the second card face of enchantment rooms before parsing`() {
        val enchantmentRoomCard = MagicCard(
            id = UUID.randomUUID().toString(),
            set = "any-set",
            lang = "any-lang",
            cardFaces = listOf(
                MagicCardFace(
                    name = DualLanguageText("", ""),
                    texts = DualLanguageText(
                        original = "original text",
                        translation = "１枚以上のカードがあなたの墓地を離れるたび..."
                    )
                ),
                MagicCardFace(
                    name = DualLanguageText("", ""),
                    DualLanguageText(
                        original = "original text",
                        translation = "解（かい）剖（ぼう）室（しつ）\noB\nあなたがこのドアを開放したとき..."
                    )
                )
            ),
        )

        every { japaneseParserProvider.parse(any()) } returns listOf("開放")

        val actual = getAllVocabularyFromCard.getVocabulary(enchantmentRoomCard)
        val expected = setOf("開放")

        assertThat(actual).isEqualTo(expected)

        val expectedParserInput =
            "１枚以上のカードがあなたの墓地を離れるたび... 解（かい）剖（ぼう）室（しつ）\noB\nあなたがこのドアを開放したとき..."
        verify { japaneseParserProvider.parse(expectedParserInput) }
    }

}