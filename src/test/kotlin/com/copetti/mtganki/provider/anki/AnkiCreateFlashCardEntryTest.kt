package com.copetti.mtganki.provider.anki

import com.copetti.mtg.deck.domain.model.FlashCard
import com.copetti.mtg.deck.provider.anki.AnkiCreateFlashCardEntry
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AnkiCreateFlashCardEntryTest {


    @InjectMockKs
    private lateinit var ankiCreateFlashCardEntry: AnkiCreateFlashCardEntry

    @Test
    fun `should create an anki flash card entry without any tags`() {
        val flashCard = FlashCard(
            front = "the-front",
            back = "the-back",
            tags = setOf()
        )

        val actual = ankiCreateFlashCardEntry.create(flashCard)
        val expected = "the-front\tthe-back\n"

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should create an anki flash card entry tags`() {
        val flashCard = FlashCard(
            front = "the-front",
            back = "the-back",
            tags = setOf("first-tag", "second-tag")
        )

        val actual = ankiCreateFlashCardEntry.create(flashCard)
        val expected = "the-front\tthe-back\tfirst-tag,second-tag\n"

        assertThat(actual).isEqualTo(expected)
    }


}