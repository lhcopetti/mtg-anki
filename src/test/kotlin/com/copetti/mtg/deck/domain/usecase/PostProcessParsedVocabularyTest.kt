package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.ParsedVocabularies
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PostProcessParsedVocabularyTest {

    @InjectMocks
    private lateinit var postProcessParsedVocabulary: PostProcessParsedVocabulary


    @Test
    fun `should remove vocabularies that do not contain any kanji`() {
        val input = listOf(
            ParsedVocabularies.given(vocabulary = "クリーチャー"),
            ParsedVocabularies.given(vocabulary = "です"),
            ParsedVocabularies.given(vocabulary = "取り除く"),
            ParsedVocabularies.given(vocabulary = "逡巡")
        )
        val actual = postProcessParsedVocabulary.process(input)
        val expected = setOf(
            "取り除く",
            "逡巡"
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should keep individual color vocabulary`() {

        val input = listOf(
            ParsedVocabularies.given("白"),
            ParsedVocabularies.given("青"),
            ParsedVocabularies.given("黒"),
            ParsedVocabularies.given("赤"),
            ParsedVocabularies.given("緑"),
        )
        val actual = postProcessParsedVocabulary.process(input)
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

        val input = listOf(
            ParsedVocabularies.given("黒赤"),
            ParsedVocabularies.given("黒赤緑"),
            ParsedVocabularies.given("青"),
            ParsedVocabularies.given("白青赤緑"),
            ParsedVocabularies.given("白青黒赤緑"),
            ParsedVocabularies.given("黒"),
            ParsedVocabularies.given("白青"),
            ParsedVocabularies.given("青黒"),
            ParsedVocabularies.given("赤緑"),
            ParsedVocabularies.given("黒赤青"),
        )
        val actual = postProcessParsedVocabulary.process(input)
        val expected = setOf(
            "青",
            "黒",
        )
        assertThat(actual).isEqualTo(expected)
    }
}