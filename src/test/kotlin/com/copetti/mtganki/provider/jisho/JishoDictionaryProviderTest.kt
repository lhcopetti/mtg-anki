package com.copetti.mtganki.provider.jisho

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class JishoDictionaryProviderTest {

    @MockK
    private lateinit var jishoClient: JishoClient

    @InjectMockKs
    private lateinit var jishoDictionaryProvider: JishoDictionaryProvider

    @Test
    fun `should return null definition if no data is returned`() {
        val vocabulary = "vocab"

        val response = JishoSearchResponse(data = listOf())
        every { jishoClient.searchWord(any()) } returns response

        val actual = jishoDictionaryProvider.lookup(vocabulary)
        assertThat(actual).isEqualTo(null)

        verify { jishoClient.searchWord(vocabulary) }
    }
}