package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.FlashCard
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import com.copetti.mtg.deck.gateway.VocabularyDefinition
import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.domain.mock.MagicSets
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreateFlashCardTest {

    @InjectMockKs
    private lateinit var createFlashCard: CreateFlashCard

    @Test
    fun `should create a flashcard correctly for a single faced magic card`() {
        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCard(
                vocabulary = "the-vocab",
                definition = VocabularyDefinition(
                    reading = "the-reading",
                    definitions = listOf(
                        "first-definition",
                        "second-definition"
                    )
                ),
                cards = setOf(
                    MagicCards.givenSingleFacedCard(
                        cardText = "the-original-card-text",
                        translationCardText = "the-translation-text (the-vocab)"
                    )
                ),
                sets = setOf(MagicSets.givenMagicSet())
            )
        )

        val actual = createFlashCard.create(request)
        val expected = FlashCard(
            front = "the-vocab",
            back = """
                the-reading
                first-definition
                second-definition
                
                the-translation-text (the-vocab)
                the-original-card-text

            """.trimIndent(),
            tags = setOf("set:any-set")
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should create a flashcard correctly for a multi faced magic card`() {
        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCard(
                vocabulary = "the-vocab",
                definition = VocabularyDefinition(
                    reading = "the-reading",
                    definitions = listOf(
                        "first-definition",
                        "second-definition"
                    )
                ),
                cards = setOf(
                    MagicCards.givenMultiFacedCard(
                        cardText = "the-original-card-text",
                        translationCardText = "the-translation-text (the-vocab)",
                        secondFaceText = "the-second-face-text",
                        secondFaceTranslationText = "the-second-face-translation-text"
                    )
                ),
                sets = setOf(MagicSets.givenMagicSet())
            )
        )

        val actual = createFlashCard.create(request)
        val expected = FlashCard(
            front = "the-vocab",
            back = """
                the-reading
                first-definition
                second-definition
                
                the-translation-text (the-vocab)
                the-original-card-text

            """.trimIndent(),
            tags = setOf("set:any-set")
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should create a flashcard correctly for a multi faced magic card when target vocabulary is on the second face`() {
        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCard(
                vocabulary = "the-target-vocab",
                definition = VocabularyDefinition(
                    reading = "the-reading",
                    definitions = listOf(
                        "first-definition",
                        "second-definition"
                    )
                ),
                cards = setOf(
                    MagicCards.givenMultiFacedCard(
                        cardText = "the-original-card-text",
                        translationCardText = "the-translation-text",
                        secondFaceText = "the-second-face-text",
                        secondFaceTranslationText = "the-second-face-translation-text (the-target-vocab)"
                    )
                ),
                sets = setOf(MagicSets.givenMagicSet())
            )
        )

        val actual = createFlashCard.create(request)
        val expected = FlashCard(
            front = "the-target-vocab",
            back = """
                the-reading
                first-definition
                second-definition
                
                the-second-face-translation-text (the-target-vocab)
                the-second-face-text

            """.trimIndent(),
            tags = setOf("set:any-set")
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should add set tags for the vocabulary from each tag`() {
        val request = CreateFlashCardEntryRequest(
            vocabularyStudyCard = VocabularyStudyCard(
                vocabulary = "the-vocab",
                definition = VocabularyDefinition(
                    reading = "the-reading",
                    definitions = listOf(
                        "first-definition",
                        "second-definition"
                    )
                ),
                cards = setOf(
                    MagicCards.givenSingleFacedCard(set = "set1"),
                    MagicCards.givenSingleFacedCard(set = "set2"),
                    MagicCards.givenSingleFacedCard(
                        cardText = "the-original-card-text",
                        translationCardText = "the-translation-text (the-vocab)",
                        set = "set1"
                    ),
                ),
                sets = setOf(MagicSets.givenMagicSet())
            )
        )

        val actual = createFlashCard.create(request)
        val expected = FlashCard(
            front = "the-vocab",
            back = """
                the-reading
                first-definition
                second-definition
                
                the-translation-text (the-vocab)
                the-original-card-text

            """.trimIndent(),
            tags = setOf("set:set1", "set:set2")
        )

        assertThat(actual).isEqualTo(expected)
    }
}