package com.copetti.mtganki.domain.usecase

import com.copetti.mtganki.domain.mock.MagicCards
import com.copetti.mtganki.domain.model.FlashCard
import com.copetti.mtganki.domain.model.VocabularyStudyCard
import com.copetti.mtganki.gateway.VocabularyDefinition
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
                )
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
                )
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
                )
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
                    MagicCards.givenSingleFacedCard(set = "set2" ),
                    MagicCards.givenSingleFacedCard(
                        cardText = "the-original-card-text",
                        translationCardText = "the-translation-text (the-vocab)",
                        set = "set1"
                    ),
                )
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