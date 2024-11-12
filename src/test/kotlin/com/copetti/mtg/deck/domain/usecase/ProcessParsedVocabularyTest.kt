package com.copetti.mtg.deck.domain.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ProcessParsedVocabularyTest {

    @InjectMocks
    private lateinit var processParsedVocabulary: ProcessParsedVocabulary


    @Test
    fun `should remove vocabularies that do not contain any kanji`() {
        val input = setOf(
            "クリーチャー",
            "です",
            "取り除く",
            "逡巡"
        )
        val actual = processParsedVocabulary.process(input)
        val expected = setOf(
            "取り除く",
            "逡巡"
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should keep individual color vocabulary`() {

        val input = setOf(
            "白",
            "青",
            "黒",
            "赤",
            "緑",
        )
        val actual = processParsedVocabulary.process(input)
        val expected = setOf(
            "白",
            "青",
            "黒",
            "赤",
            "緑"
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should remove color combinations`() {

        val input = setOf(
            "黒赤",
            "黒赤緑",
            "青",
            "白青赤緑",
            "白青黒赤緑",
            "黒",
            "白青",
            "青黒",
            "赤緑",
            "黒赤青",
        )
        val actual = processParsedVocabulary.process(input)
        val expected = setOf(
            "青",
            "黒",
        )
        assertThat(actual).isEqualTo(expected)
    }
}