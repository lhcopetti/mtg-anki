#!/bin/bash
set -e


echo "Building anki-deck-creator application"

echo "Running tests for the anki-deck-creator application..."

python -m unittest discover tests -v


echo "Building python executable for the anki-deck-creator application..."

pyinstaller --onefile create_anki_deck/__main__.py -n mtg-anki-deck-creator

echo "Executable file is ready at: ./dist/mtg-anki-deck-creator"
