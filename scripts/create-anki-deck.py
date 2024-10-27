import random
import genanki
import sys

MTG_ANKI_MODEL_ID=1277901333
MTG_ANKI_DECK_ID=1454387444
MTG_ANKI_TEMPLATE_ID=3732922555

MTG_ANKI_FRONT_FIELD_ID=3732666555
MTG_ANKI_BACK_FIELD_ID=3732777555

DEFAULT_TAGS = ['mtg-anki']

class MtgAnkiNote(genanki.Note):
  @property
  def guid(self):
    return genanki.guid_for(self.fields[0])


exportFilePath = sys.argv[1]

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

for line in sys.stdin:
    front, back = line.split('\t')
    note = MtgAnkiNote(
        model = mtgAnkiModel,
        fields=[front, back],
        tags = DEFAULT_TAGS
        )
    notes.append(note)


mtgAnkiDeck = genanki.Deck(
        MTG_ANKI_DECK_ID,
        'Magic: The Gathering (mtg-anki)'
        )
for note in notes: mtgAnkiDeck.add_note(note)
genanki.Package([mtgAnkiDeck]).write_to_file(exportFilePath)
