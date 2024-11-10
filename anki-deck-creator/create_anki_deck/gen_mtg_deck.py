
import genanki
from create_anki_deck.flash_card_parser import FlashCard

MTG_ANKI_MODEL_ID=1277901333
MTG_ANKI_DECK_ID=1454387444
MTG_ANKI_TEMPLATE_ID=3732922555

MTG_ANKI_FRONT_FIELD_ID=3732666555
MTG_ANKI_BACK_FIELD_ID=3732777555

class MtgAnkiDeckRequest:
    def __init__(self, export_file_path: str, flash_cards: FlashCard):
        self.export_file_path = export_file_path
        self.flash_cards = flash_cards


class MtgAnkiNote(genanki.Note):
  @property
  def guid(self):
    return genanki.guid_for(self.fields[0])


def gen_mtg_deck(request: MtgAnkiDeckRequest):

    mtgAnkiModel = genanki.Model(
            MTG_ANKI_MODEL_ID,
            'MTG Anki Model',
            fields = [
                { 'id': str(MTG_ANKI_FRONT_FIELD_ID), 'name': 'Front' },
                { 'id': str(MTG_ANKI_BACK_FIELD_ID), 'name': 'Back' }
                ],
            templates = [
                {
                    'id': MTG_ANKI_TEMPLATE_ID,
                    'name': 'MTG Anki Template',
                    'qfmt': "<div style='font-size: 60px'>{{Front}}</div>",
                    'afmt': '{{FrontSide}}<hr id="answer">{{Back}}'
                    }
                ]
            )

    notes = []

    for flash_card in request.flash_cards:

        note = MtgAnkiNote(
            model = mtgAnkiModel,
            fields=[flash_card.front, flash_card.back],
            tags = flash_card.tags
            )
        notes.append(note)


    mtgAnkiDeck = genanki.Deck(
            MTG_ANKI_DECK_ID,
            'Play Magic: The Gathering in japanese'
            )
    for note in notes: mtgAnkiDeck.add_note(note)
    genanki.Package([mtgAnkiDeck]).write_to_file(request.export_file_path)

