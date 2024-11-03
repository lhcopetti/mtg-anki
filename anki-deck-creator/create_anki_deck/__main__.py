
import sys
from create_anki_deck.flash_card_parser import parse_flash_cards
from create_anki_deck.gen_mtg_deck import gen_mtg_deck
from create_anki_deck.gen_mtg_deck import MtgAnkiDeckRequest


if __name__ == "__main__":

    if len(sys.argv) < 2:
        print("This script should be called with a file name. Eg: 'python create-anki-deck.py deck-name.apkg'")
        sys.exit(1)

    export_file_path = sys.argv[1]

    flash_cards = parse_flash_cards(sys.stdin)

    request = MtgAnkiDeckRequest(export_file_path, flash_cards)
    gen_mtg_deck(request)
