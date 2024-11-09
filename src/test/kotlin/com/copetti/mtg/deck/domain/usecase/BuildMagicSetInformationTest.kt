package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.MagicSets
import com.copetti.mtg.deck.domain.mock.VocabularyStudyCards
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class BuildMagicSetInformationTest {


    @InjectMockKs
    private lateinit var buildMagicSetInformation: BuildMagicSetInformation

    @Test
    fun `should create a set line correctly for only one set`() {
        val sets = setOf(
            MagicSets.givenMagicSet(name = "the-set")
        )

        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCards.givenVocabularyStudyCard(sets = sets)
        )
        val actual = buildMagicSetInformation.build(request)
        val expected = "Present in set: \"the-set\"."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should create a set line correctly for three sets`() {
        val sets = setOf(
            MagicSets.givenMagicSet(name = "the-set"),
            MagicSets.givenMagicSet(name = "the-second-one"),
            MagicSets.givenMagicSet(name = "the-third")
        )

        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCards.givenVocabularyStudyCard(sets = sets)
        )
        val actual = buildMagicSetInformation.build(request)
        val expected = "Present in sets: \"the-set\", \"the-second-one\", \"the-third\"."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should create a set line correctly for more than three sets`() {
        val sets = setOf(
            MagicSets.givenMagicSet(name = "the-set"),
            MagicSets.givenMagicSet(name = "the-second-one"),
            MagicSets.givenMagicSet(name = "the-third"),
            MagicSets.givenMagicSet(name = "the-fourth"),
            MagicSets.givenMagicSet(name = "the-fifth"),
        )

        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCards.givenVocabularyStudyCard(sets = sets)
        )
        val actual = buildMagicSetInformation.build(request)
        val expected = "Present in sets: \"the-set\", \"the-second-one\", \"the-third\" and 2 more."

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should create a set line correctly for more than three sets and show the most recent sets first`() {
        val sets = setOf(
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 1, 1), name = "the-set"),
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 8, 1), name = "the-second-one"),
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 3, 1), name = "the-third"),
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 12, 1), name = "the-fourth"),
            MagicSets.givenMagicSet(releaseDate = LocalDate.of(2023, 5, 1), name = "the-fifth"),
        )

        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCards.givenVocabularyStudyCard(sets = sets)
        )
        val actual = buildMagicSetInformation.build(request)
        val expected = "Present in sets: \"the-fourth\", \"the-second-one\", \"the-fifth\" and 2 more."

        assertThat(actual).isEqualTo(expected)
    }


}