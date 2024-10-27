package com.copetti.mtganki.provider.kuromoji

import com.atilika.kuromoji.ipadic.Tokenizer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class KuromojiJishoJapaneseParserProviderTest {

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
            "俺",
            "が",
            "大好き",
            "です",
            "。"
        )
        assertThat(output).isEqualTo(expected)
    }

    @Test
    fun `should return the dictionary form of adjectives`() {
        val input = "素晴らしかったです。"
        val output = kuromojiJapaneseParserProvider.parse(input)

        val expected = listOf(
            "素晴らしい",
            "た",
            "です",
            "。"
        )
        assertThat(output).isEqualTo(expected)
    }

    @Test
    fun `should return the dictionary form of verbs in te-form`() {
        val input = "兄がピアノを弾いて、妹が歌った。"
        val output = kuromojiJapaneseParserProvider.parse(input)

        val expected = listOf(
            "兄",
            "が",
            "ピアノ",
            "を",
            "弾く",
            "て",
            "、",
            "妹",
            "が",
            "歌う",
            "た",
            "。"
        )
        assertThat(output).isEqualTo(expected)
    }

    @Test
    fun `should return the dictionary form of verbs in stem-form`() {
        val input = "兄がピアノを弾き、妹が歌った。"
        val output = kuromojiJapaneseParserProvider.parse(input)

        val expected = listOf(
            "兄",
            "が",
            "ピアノ",
            "を",
            "弾く",
            "、",
            "妹",
            "が",
            "歌う",
            "た",
            "。"
        )
        assertThat(output).isEqualTo(expected)
    }

}