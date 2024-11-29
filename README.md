# MTG-Anki
**A collection of Magic: The Gathering-based Anki decks for Japanese learners.**

> **⚠️ Work in Progress:**  
Expect changes, and feel free to contribute by submitting issues or suggestions.

## Overview
This project provides Anki decks designed for learners of Japanese, based on the popular trading card game *Magic: The Gathering* (MTG). Each deck focuses on vocabulary and kanji found in the text of MTG cards, offering a fun and practical way to expand your Japanese language skills while enjoying the game.

### Getting Started

To get the latest Anki deck, visit the [Releases page](https://github.com/lhcopetti/mtg-anki/releases) and download the most recent version. Once downloaded, simply import the deck into Anki to start learning.

### Focusing on Specific Sets

Anki’s custom study feature allows you to target specific sets, which is useful for focused study sessions or draft preparation. To study vocabulary from a particular Magic: The Gathering set:

1. Open your deck in Anki.
2. Click on **Custom Study** and select **Study by card tag**.
3. Use the set tag to filter cards, for example, enter `set:dsk` to review all vocabulary from *Duskmourn*.

This way, you can prepare specifically for upcoming drafts or focus on vocabulary from your favorite sets.

### Example

![image](https://github.com/user-attachments/assets/90116376-e8cd-4996-b5dd-45b4c4114480)

![image](https://github.com/user-attachments/assets/bfabf513-ae95-4f73-9721-7ad9dfa6f3c6)

## Development

#### TODOs:
- [ ] Add instructions for custom study
- [ ] Add card frequency count to vocabulary
- [X] Fix issue where cards have way too many definitions (eg: 引く)
- [X] Fix issue where some cards are missing the sample sentence (card vocabulary not in base form)
- [X] Remove unnecessary vocabulary such as color combinations (eg: 緑青)
- [X] Output .apkg file name based on the version from maven artifact
- [X] Some cards do not have sample sentences or definition
- [X] Add support for custom tags (e.g., set name, game platform, core, common)
- [ ] Add support for all sets
- [X] Remove known conjugating words (e.g., torinozoku and torinozoki)
- [ ] Include hiragana and katakana only words?
- [ ] Remove vocabularies that don't have a properly matching definition
- [X] Handle improper formatting of room names (second face contains the name of the card)
- [ ] Build the project from 'mvn clean install' instead of build.sh
- [X] Incorporate downloading a fresh export from scryfall project during build
- [X] Limit the number of definitions
- [ ] Add link to reference card (sentence)
- [ ] Consider a sub-deck structure
- [ ] Consider adding decks for card names

### Card Format

Each Anki card is designed to help you learn Japanese vocabulary using terms from Magic: The Gathering (MTG). Here’s how the format works:

- **Front**: Displays the target vocabulary word, which is the main term you’re learning.

- **Back**:
    - **Reading**: The pronunciation of the vocabulary in kana.
    - **Definitions**: A list of possible meanings or translations for the vocabulary term.
    - *(Empty line)*
    - **Set Information**: Details about the MTG set or expansions in which this vocabulary appears.
    - *(Empty line)*
    - **Example Sentence**: A sample sentence drawn from the official MTG ruling text, giving context for the vocabulary usage.

This structure provides a complete context for each term, linking it to MTG gameplay and rules while also making vocabulary and reading practice accessible.


## Background
As a Magic: The Gathering player, I often considered switching the language settings in *Magic: Arena* to Japanese. However, I felt overwhelmed by the sheer number of cards and the vast vocabulary required to comfortably play the game in a foreign language. Since MTG matches tend to be fast-paced, there isn’t much time to look up unfamiliar kanji or vocabulary mid-game, even though playing in Japanese seemed like a great language-learning tool.

That changed when I began playing MTG in the *Draft* format. In draft, you build your deck from a smaller pool of cards from a specific set, rather than the entire card collection. This smaller scope gave me the idea to create Anki decks focused on the vocabulary found in individual set expansions, providing learners with a more manageable and structured way to engage with Japanese through MTG.

## Features
- **MTG Set-Based Decks:** Anki decks are created from specific MTG set expansions, helping you focus on the vocabulary that you’ll encounter while playing that set.
- **Japanese Card Text Parsing:** Card text is processed using a Japanese parser (MeCab) to extract vocabulary, including kanji and readings.
- **Jisho API Integration:** Definitions and readings are fetched from the Jisho API to provide accurate and relevant information for each vocabulary item.
- **Contextual Learning:** Example sentences are generated directly from the MTG cards, helping you learn vocabulary in context.

## Tools Used
- **[Kuromoji](https://github.com/atilika/kuromoji):** A powerful Japanese parser used to break down the card text into vocabulary.
- **[Jisho API](https://jisho.org/):** An API that provides dictionary definitions and readings for the Japanese terms found on the cards.
- **[Scryfall](https://scryfall.com/):** A comprehensive database of all MTG cards, used to gather data about card sets and translations.


