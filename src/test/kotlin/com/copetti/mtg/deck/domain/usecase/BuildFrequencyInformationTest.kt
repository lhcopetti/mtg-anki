package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.domain.mock.MagicSets
import com.copetti.mtg.deck.domain.mock.VocabularyStudyCards
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class BuildFrequencyInformationTest {


    @InjectMockKs
    private lateinit var buildFrequencyInformation: BuildFrequencyInformation

    @Test
    fun `should create a set line correctly for only one set`() {
        val setCode = "the-set"
        val sets = setOf(
            MagicSets.givenMagicSet(name = "the-set-name", code = setCode)
        )
        val vocabularyCard = VocabularyStudyCards.givenVocabularyStudyCard(
            cards = setOf(
                MagicCards.givenSingleFacedCard(
                    set = setCode
                )
            ),
            sets = sets
        )

        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = vocabularyCard
        )
        val actual = buildFrequencyInformation.build(request)
        val expected = "Appears 1 time.\nPresent in set: \"the-set-name\" (x1)."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should create a set line correctly for three sets`() {
        val sets = setOf(
            MagicSets.givenMagicSet(name = "the-set", code = "first"),
            MagicSets.givenMagicSet(name = "the-second-one", code = "second"),
            MagicSets.givenMagicSet(name = "the-third", code = "third")
        )
        val cards = setOf(
            MagicCards.givenSingleFacedCard(name = "first-1", set = "first"),
            MagicCards.givenSingleFacedCard(name = "second-1", set = "second"),
            MagicCards.givenSingleFacedCard(name = "second-2", set = "second"),
            MagicCards.givenSingleFacedCard(name = "third-1", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-2", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-3", set = "third"),
        )

        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCards.givenVocabularyStudyCard(
                cards = cards,
                sets = sets
            )
        )
        val actual = buildFrequencyInformation.build(request)
        val expected =
            "Appears 6 times.\nPresent in sets: \"the-set\" (x1), \"the-second-one\" (x2), \"the-third\" (x3)."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should create a set line correctly for more than three sets`() {
        val sets = setOf(
            MagicSets.givenMagicSet(name = "the-set", code = "first"),
            MagicSets.givenMagicSet(name = "the-second-one", code = "second"),
            MagicSets.givenMagicSet(name = "the-third", code = "third"),
            MagicSets.givenMagicSet(name = "the-fourth", code = "fourth"),
            MagicSets.givenMagicSet(name = "the-fifth", code = "fifth"),
        )
        val cards = setOf(
            MagicCards.givenSingleFacedCard(name = "first-1", set = "first"),
            MagicCards.givenSingleFacedCard(name = "second-1", set = "second"),
            MagicCards.givenSingleFacedCard(name = "second-2", set = "second"),
            MagicCards.givenSingleFacedCard(name = "third-1", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-2", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-3", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-4", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-5", set = "third"),
            MagicCards.givenSingleFacedCard(name = "fourth-2", set = "fourth"),
            MagicCards.givenSingleFacedCard(name = "fourth-3", set = "fourth"),
            MagicCards.givenSingleFacedCard(name = "fifth-1", set = "fifth"),
        )

        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCards.givenVocabularyStudyCard(
                sets = sets,
                cards = cards
            )
        )
        val actual = buildFrequencyInformation.build(request)
        val expected =
            "Appears 11 times.\nPresent in sets: \"the-set\" (x1), \"the-second-one\" (x2), \"the-third\" (x5) and 2 more."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should create a set line correctly for more than three sets and show the most recent sets first`() {
        val sets = setOf(
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 1, 1), name = "the-set", code = "first"),
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 8, 1), name = "the-second-one", code = "second"),
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 3, 1), name = "the-third", code = "third"),
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 12, 1), name = "the-fourth", code = "fourth"),
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 5, 1), name = "the-fifth", code = "fifth"),
        )
        val cards = setOf(
            MagicCards.givenSingleFacedCard(name = "first-1", set = "first"),
            MagicCards.givenSingleFacedCard(name = "second-1", set = "second"),
            MagicCards.givenSingleFacedCard(name = "second-2", set = "second"),
            MagicCards.givenSingleFacedCard(name = "third-1", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-2", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-3", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-4", set = "third"),
            MagicCards.givenSingleFacedCard(name = "third-5", set = "third"),
            MagicCards.givenSingleFacedCard(name = "fourth-2", set = "fourth"),
            MagicCards.givenSingleFacedCard(name = "fourth-3", set = "fourth"),
            MagicCards.givenSingleFacedCard(name = "fifth-1", set = "fifth"),
        )

        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCards.givenVocabularyStudyCard(
                sets = sets,
                cards = cards
            )
        )
        val actual = buildFrequencyInformation.build(request)
        val expected =
            "Appears 11 times.\nPresent in sets: \"the-fourth\" (x2), \"the-second-one\" (x2), \"the-fifth\" (x1) and 2 more."

        assertThat(actual).isEqualTo(expected)
    }


}