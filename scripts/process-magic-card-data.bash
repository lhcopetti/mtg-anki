#!/bin/bash -e

jq -c 'select(.set == "dsk") |
    .target_text += if has("printed_text") then [.printed_text] else [] end |
    .target_text += if has("card_faces") then [.card_faces[].printed_text] else [] end |
    { id, name, oracle_text, printed_text, set, printed_name, target_text }' |\
bash scripts/add-clean-target-text.bash |\
jq '.clean_target_text' |\
python scripts/parser.py

