#!/bin/bash -e

while IFS= read -r line; do

    # Removes card names from target text
    printedName="$(echo "$line" | jq -r '.printed_name')"
    targetText="$(echo "$line" | jq '.target_text')"

    echo "$line" | jq -c '.clean_target_text = (.target_text | map(gsub('"\"$printedName\""'; "")))'
done;


