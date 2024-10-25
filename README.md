# MTG-Anki
**A collection of Magic: The Gathering-based Anki decks for Japanese learners.**

> **⚠️ Work in Progress:**  
Expect changes, and feel free to contribute by submitting issues or suggestions.

## Overview
This project provides Anki decks designed for learners of Japanese, based on the popular trading card game *Magic: The Gathering* (MTG). Each deck focuses on vocabulary and kanji found in the text of MTG cards, offering a fun and practical way to expand your Japanese language skills while enjoying the game.

### Quick Start
To find the decks, head straight to the [Releases](https://github.com/lhcopetti/mtg-anki/releases) page and download the Anki decks for your desired MTG set expansion.

### Example

![example-review](https://github.com/user-attachments/assets/eae65b1b-7008-4162-947e-fc14b2af35f0)


## Development

#### TODOs:
- [ ] Remove unnecessary vocabulary such as color combinations (eg: 緑青)
- [ ] Some cards do not have sample sentences or definition
- [ ] Add more tags (e.g., set name, game platform, core, rarity?)
- [ ] Add support for all sets
- [ ] Have a single build script do everything from export to apkg
- [ ] Remove known conjugating words (e.g., torinozoku and torinozoki)
- [ ] Include hiragana and katakana only words
- [ ] Remove invalid words
- [ ] Add better support for two-faced cards (enchantment room)
- [ ] Handle improper formatting of room names (second face contains the name of the card)

#### Enhancements:
- [ ] Improve formatting of notes
- [ ] Include frequency data
- [ ] Add link to original card from sample sentence
- [ ] Link to search for other cards containing that vocabulary
- [ ] Add WaniKani level and JLPT if present
- [ ] Add another two sentences for context outside of MTG
- [ ] Include a deck for card names

#### Vocabulary to check:
- [ ] 支払え
- [ ] 広
- [ ] 緑青


## Background
As a Magic: The Gathering player, I often considered switching the language settings in *Magic: Arena* to Japanese. However, I felt overwhelmed by the sheer number of cards and the vast vocabulary required to comfortably play the game in a foreign language. Since MTG matches tend to be fast-paced, there isn’t much time to look up unfamiliar kanji or vocabulary mid-game, even though playing in Japanese seemed like a great language-learning tool.

That changed when I began playing MTG in the *Draft* format. In draft, you build your deck from a smaller pool of cards from a specific set, rather than the entire card collection. This smaller scope gave me the idea to create Anki decks focused on the vocabulary found in individual set expansions, providing learners with a more manageable and structured way to engage with Japanese through MTG.

## Features
- **MTG Set-Based Decks:** Anki decks are created from specific MTG set expansions, helping you focus on the vocabulary that you’ll encounter while playing that set.
- **Japanese Card Text Parsing:** Card text is processed using a Japanese parser (MeCab) to extract vocabulary, including kanji and readings.
- **Jisho API Integration:** Definitions and readings are fetched from the Jisho API to provide accurate and relevant information for each vocabulary item.
- **Contextual Learning:** Example sentences are generated directly from the MTG cards, helping you learn vocabulary in context.

## Tools Used
- **[MeCab](https://taku910.github.io/mecab/):** A powerful Japanese parser used to break down the card text into vocabulary.
- **[Jisho API](https://jisho.org/):** An API that provides dictionary definitions and readings for the Japanese terms found on the cards.
- **[Scryfall](https://scryfall.com/):** A comprehensive database of all MTG cards, used to gather data about card sets and translations.
- **Scripts:** A lot of bash scripts, json manipulation with jq and unsafe calls. For now...

## Development

**To-Do**
