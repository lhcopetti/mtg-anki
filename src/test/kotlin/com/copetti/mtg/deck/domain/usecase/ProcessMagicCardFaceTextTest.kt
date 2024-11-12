package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.MagicCardFaces
import com.copetti.mtg.deck.domain.mock.MagicCards
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
        val enchantmentRoomCard = MagicCards.givenSingleFacedCard(
            translationName = "永劫の無垢",
            text = "Lifelink\nWhenever one or more other creatures...",
            translationText = "絆魂\n...\n永劫の無垢が死亡したとき...",
        )

        val actual = processMagicCardFaceText.process(enchantmentRoomCard)
        val expected = "絆魂\n...\nが死亡したとき..."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should remove invalid starting lines from the second card face of enchantment rooms`() {
        val enchantmentRoomCard = MagicCards.givenSingleFacedCard(
            manaCost = "{B}",
            translationText = "解（かい）剖（ぼう）室（しつ）\noB\nあなたがこのドアを開放したとき..."
        )

        val actual = processMagicCardFaceText.process(enchantmentRoomCard)
        val expected = "あなたがこのドアを開放したとき..."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should remove invalid starting lines from the second card face of enchantment rooms multicolored`() {
        val enchantmentRoomCard = MagicCards.givenSingleFacedCard(
            manaCost = "{3}{U}{U}",
            translationText = "冠（かん）水（すい）した食（しょく）堂（どう）\no3oUoU\nあなたがこのドアを開放したとき、カード３枚を引き、その後、カード１枚を捨てる。"
        )

        val actual = processMagicCardFaceText.process(enchantmentRoomCard)
        val expected = "あなたがこのドアを開放したとき、カード３枚を引き、その後、カード１枚を捨てる。"

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should remove the name of the card from the opposite face correctly`() {
        val enchantmentRoomCard = MagicCards.givenMultiFacedCard(
            listOf(
                MagicCardFaces.givenMagicCardFaces(
                    translationName = "千の月の鍛冶場",
                    translationText = "千の月の鍛冶場が戦場に出たとき、「このクリーチャーのパワーとタフネスはそれぞれ、アーティファクトやクリーチャーでありあなたがコントロールしているものの数に等しい。」を持つ白のノーム・兵士・アーティファクト・クリーチャー・トークン１体を生成する。\nあなたの戦闘前メイン・フェイズの開始時に、アーティファクトやクリーチャーでありあなたがコントロールしていてアンタップ状態である５つをタップしてもよい。そうしたなら、千の月の鍛冶場を変身させる。",
                ),
                MagicCardFaces.givenMagicCardFaces(
                    text = "千の兵舎",
                    translationText = "（千の月の鍛冶場から変身する。）\n{T}：{W}を加える。\nあなたが千の兵舎によって生み出されたマナを使ってアーティファクトやクリーチャーである呪文１つを唱えるたび、「このクリーチャーのパワーとタフネスはそれぞれ、アーティファクトやクリーチャーでありあなたがコントロールしているものの数に等しい。」を持つ白のノーム・兵士・アーティファクト・クリーチャー・トークン１体を生成する。"
                )
            )
        )

        val actual = processMagicCardFaceText.process(enchantmentRoomCard)
        val expected =
            "が戦場に出たとき、「このクリーチャーのパワーとタフネスはそれぞれ、アーティファクトやクリーチャーでありあなたがコントロールしているものの数に等しい。」を持つ白のノーム・兵士・アーティファクト・クリーチャー・トークン１体を生成する。\nあなたの戦闘前メイン・フェイズの開始時に、アーティファクトやクリーチャーでありあなたがコントロールしていてアンタップ状態である５つをタップしてもよい。そうしたなら、を変身させる。 （から変身する。）\n{T}：{W}を加える。\nあなたが千の兵舎によって生み出されたマナを使ってアーティファクトやクリーチャーである呪文１つを唱えるたび、「このクリーチャーのパワーとタフネスはそれぞれ、アーティファクトやクリーチャーでありあなたがコントロールしているものの数に等しい。」を持つ白のノーム・兵士・アーティファクト・クリーチャー・トークン１体を生成する。"

        assertThat(actual).isEqualTo(expected)
    }
}