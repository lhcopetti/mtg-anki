
import unittest

from datetime import datetime
from create_anki_deck.flash_card_parser import parse_flash_cards
from create_anki_deck.flash_card_parser import FlashCard


def getDefaultTags():
    today = datetime.today().strftime("%Y-%m-%d")
    return ['mtg-anki', f'updated-{today}']

class TestParseFlashCards(unittest.TestCase):

    def test_parse_single_entry(self):
        input = ["123\t456"]
        actual = parse_flash_cards(input)
        expected = [FlashCard("123", "456", getDefaultTags())]
        self.assertEqual(actual, expected)

    def test_parse_multiple_entries(self):
        input = [
                "123\t456",
                "789\t012",
                "abc\tdef"
        ]
        actual = parse_flash_cards(input)
        expected = [
                FlashCard("123", "456", getDefaultTags()),
                FlashCard("789", "012", getDefaultTags()),
                FlashCard("abc", "def", getDefaultTags()),
        ]
        self.assertEqual(actual, expected)

    def test_parse_entry_with_custom_tags(self):
        input = [
                "123\t456\ttag1",
                "789\t012\ttag2,tag3",
                "abc\tdef\ttag3,tag4,tag5"
        ]
        actual = parse_flash_cards(input)
        expected = [
                FlashCard("123", "456", getDefaultTags() + ["tag1"]),
                FlashCard("789", "012", getDefaultTags() + ["tag2", "tag3"]),
                FlashCard("abc", "def", getDefaultTags() + ["tag3", "tag4", "tag5"]),
        ]
        self.assertEqual(actual, expected)

    def test_parse_entry_with_invalid_tags(self):
        input = [
                "123\t456\t",
                "789\t012\t,",
                "abc\tddd\t,,,",
                "def\teee\t  ,   ,   ",
                "ghi\tfff\t,",
                "jkl\tggg\t,\n",
        ]
        actual = parse_flash_cards(input)
        expected = [
                FlashCard("123", "456", getDefaultTags()),
                FlashCard("789", "012", getDefaultTags()),
                FlashCard("abc", "ddd", getDefaultTags()),
                FlashCard("def", "eee", getDefaultTags()),
                FlashCard("ghi", "fff", getDefaultTags()),
                FlashCard("jkl", "ggg", getDefaultTags()),
        ]
        self.assertEqual(actual, expected)
if __name__ == '__main__':
    unittest.main()
