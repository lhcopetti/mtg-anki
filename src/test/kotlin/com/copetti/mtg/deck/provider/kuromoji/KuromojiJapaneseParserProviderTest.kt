package com.copetti.mtg.deck.provider.kuromoji

import com.atilika.kuromoji.ipadic.Tokenizer
import com.copetti.mtg.deck.domain.model.ParsedVocabulary
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class KuromojiJapaneseParserProviderTest {

    private lateinit var kuromojiJapaneseParserProvider: KuromojiJapaneseParserProvider

    @BeforeEach
    fun setUp() {
        kuromojiJapaneseParserProvider = KuromojiJapaneseParserProvider(Tokenizer())
    }

    @Test
    fun `should break a sentence into its vocabulary components`() {
        val input = "俺が大好きです。"
        val output = kuromojiJapaneseParserProvider.parse(input)

        val expected = listOf(
            ParsedVocabulary(vocabulary = "俺", baseForm = "俺"),
            ParsedVocabulary(vocabulary = "が", baseForm = "が"),
            ParsedVocabulary(vocabulary = "大好き", baseForm = "大好き"),
            ParsedVocabulary(vocabulary = "です", baseForm = "です"),
            ParsedVocabulary(vocabulary = "。", baseForm = "。")
        )
        assertThat(output).isEqualTo(expected)
    }

    @Test
    fun `should return the dictionary form of adjectives`() {
        val input = "素晴らしかったです。"
        val output = kuromojiJapaneseParserProvider.parse(input)

        val expected = listOf(
            ParsedVocabulary(vocabulary = "素晴らしかっ", baseForm = "素晴らしい"),
            ParsedVocabulary(vocabulary = "た", baseForm = "た"),
            ParsedVocabulary(vocabulary = "です", baseForm = "です"),
            ParsedVocabulary(vocabulary = "。", baseForm = "。")
        )
        assertThat(output).isEqualTo(expected)
    }

    @Test
    fun `should return the dictionary form of verbs in te-form`() {
        val input = "兄がピアノを弾いて、妹が歌った。"
        val output = kuromojiJapaneseParserProvider.parse(input)

        val expected = listOf(
            ParsedVocabulary(vocabulary = "兄", baseForm = "兄"),
            ParsedVocabulary(vocabulary = "が", baseForm = "が"),
            ParsedVocabulary(vocabulary = "ピアノ", baseForm = "ピアノ"),
            ParsedVocabulary(vocabulary = "を", baseForm = "を"),
            ParsedVocabulary(vocabulary = "弾い", baseForm = "弾く"),
            ParsedVocabulary(vocabulary = "て", baseForm = "て"),
            ParsedVocabulary(vocabulary = "、", baseForm = "、"),
            ParsedVocabulary(vocabulary = "妹", baseForm = "妹"),
            ParsedVocabulary(vocabulary = "が", baseForm = "が"),
            ParsedVocabulary(vocabulary = "歌っ", baseForm = "歌う"),
            ParsedVocabulary(vocabulary = "た", baseForm = "た"),
            ParsedVocabulary(vocabulary = "。", baseForm = "。"),
        )
        assertThat(output).isEqualTo(expected)
    }

    @Test
    fun `should return the dictionary form of verbs in stem-form`() {
        val input = "兄がピアノを弾き、妹が歌った。"
        val output = kuromojiJapaneseParserProvider.parse(input)

        val expected = listOf(
            ParsedVocabulary(vocabulary = "兄", baseForm = "兄"),
            ParsedVocabulary(vocabulary = "が", baseForm = "が"),
            ParsedVocabulary(vocabulary = "ピアノ", baseForm = "ピアノ"),
            ParsedVocabulary(vocabulary = "を", baseForm = "を"),
            ParsedVocabulary(vocabulary = "弾き", baseForm = "弾く"),
            ParsedVocabulary(vocabulary = "、", baseForm = "、"),
            ParsedVocabulary(vocabulary = "妹", baseForm = "妹"),
            ParsedVocabulary(vocabulary = "が", baseForm = "が"),
            ParsedVocabulary(vocabulary = "歌っ", baseForm = "歌う"),
            ParsedVocabulary(vocabulary = "た", baseForm = "た"),
            ParsedVocabulary(vocabulary = "。", baseForm = "。"),
        )
        assertThat(output).isEqualTo(expected)
    }

}