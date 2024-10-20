#!/bin/bash -e


while IFS= read -r line; do
    echo "$line" | python scripts/parser.py
done;


#onlyKanjiPattern = r'[\u4E00-\u9FAF]'
#grep -o '[一-龥]'
