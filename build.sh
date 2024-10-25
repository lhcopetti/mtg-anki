#!/bin/bash -e

echo "[mtg-anki] Beginning..."

EXPORT_FILE="$1"
PRE_PROCESSED_FILE_NAME="pre-processed-export.json"

bash scripts/pre-process-magic-data-export.bash < "$EXPORT_FILE" > "$PRE_PROCESSED_FILE_NAME"

echo "[mtg-anki] Export pre-processed"

cat "$PRE_PROCESSED_FILE_NAME" |\
    bash scripts/process-magic-card-data.bash |\
    xargs -I{} bash scripts/build-anki-entry.bash "$PRE_PROCESSED_FILE_NAME" "{}" |\
    python scripts/create-anki-deck.py

echo "[mtg-anki] Anki deck generated successfully"

echo "[mtg-anki] End..."
