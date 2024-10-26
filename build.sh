#!/bin/bash -e

echo "[mtg-anki] Beginning..."

EXPORT_FILE="$1"
PRE_PROCESSED_FILE_NAME="pre-processed-export.json"

bash scripts/pre-process-magic-data-export.bash < "$EXPORT_FILE" > "$PRE_PROCESSED_FILE_NAME"
echo "[mtg-anki] Export pre-processed"


VOCABULARY_FILE="parsed_vocabulary.txt"
mvn spring-boot:run -Dspring-boot.run.arguments="$EXPORT_FILE $VOCABULARY_FILE"

if [ $? -ne 0 ]; then
    echo "Generating the deck failed. Please see log for more details"
    exit 1;
fi

echo "[mtg-anki] Generated vocabulary file"

cat "$VOCABULARY_FILE" |\
    python scripts/parser.py  |\
    xargs -I{} bash scripts/build-anki-entry.bash "$PRE_PROCESSED_FILE_NAME" "{}" |\
    python scripts/create-anki-deck.py

echo "[mtg-anki] Anki deck generated successfully"

echo "[mtg-anki] End..."

