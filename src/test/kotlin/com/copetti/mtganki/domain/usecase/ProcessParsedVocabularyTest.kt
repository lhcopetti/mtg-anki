package com.copetti.mtganki.domain.usecase

import com.copetti.mtg.deck.domain.usecase.ProcessParsedVocabulary
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

}