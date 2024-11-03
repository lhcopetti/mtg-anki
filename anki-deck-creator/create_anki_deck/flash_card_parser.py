
from datetime import datetime
from dataclasses import dataclass

@dataclass
class FlashCard:
    front: str
    back: str
    tags: list[str]


def normalizeTags(tagCsv):
    return [ tag.strip() for tag in tagCsv.split(",") if tag.strip() ] 

def buildDefaultTags(): 
    today = datetime.today().strftime("%Y-%m-%d")
    return ['mtg-anki', f'updated-{today}']


def parse_flash_cards(input):

    flash_cards = []
    defaultTags = buildDefaultTags()

    for line in input:
        components = line.split('\t')

        if len(components) < 2:
            print(f'Line was not parsed successfully, ignoring it | line: {line}')
            continue

        front = components[0]
        back = components[1]
        tags = []

        if (len(components) > 2):
            tags = normalizeTags(components[2])

        flash_card = FlashCard(front, back, defaultTags + tags)
        flash_cards.append(flash_card)

    return flash_cards
