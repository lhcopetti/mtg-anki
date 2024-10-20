

import MeCab
import re
import sys

wakati = MeCab.Tagger("-Owakati")

input = sys.stdin.read();

result = wakati.parse(input).split();

allJapanesePattern = r'[\u3040-\u30FF\u4E00-\u9FAF\u3000-\u303F]'
onlyKanjiPattern = r'[\u4E00-\u9FAF]'
pattern = onlyKanjiPattern

uniqueEntries = list(set(result))
filtered_list = [s for s in uniqueEntries if re.search(pattern, s)]

for item in filtered_list:
    print(item)
