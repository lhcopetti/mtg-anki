#!/bin/bash -e

echo "[mtg-anki] Beginning..."

EXPORT_FILE="$1"

mvn spring-boot:run -Dspring-boot.run.arguments="$EXPORT_FILE mtg-anki-deck-v0.0.3.apkg"

if [ $? -ne 0 ]; then
    echo "Generating the deck failed. Please see log for more details"
    exit 1;
fi

echo "MTG Anki deck generated successfully!"

echo "[mtg-anki] End..."

