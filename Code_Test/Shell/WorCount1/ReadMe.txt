================================================================================
https://leetcode.com/problems/word-frequency/description/
================================================================================
Test
------------------------------------------------------------
Original order by key (word).
]$ WordCount1.sh < words.txt

Hash order by key (word).
]$ WordCount2.sh < words.txt

Descending order by value (frequency), Not completed.
]$ WordCount3.sh < words.txt
------------------------------------------------------------
Descending order by value (frequency).
Accepted.

]$ sed 's/ \{1,\}/\n/g' words.txt | grep -v "^$" | sort | uniq -c | sort -r | awk '{print $2, $1}'
================================================================================
Other solutions
------------------------------------------------------------
]$ cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -r | awk '{ print $2, $1 }'
]$ tr -s ' ' '\n' < words.txt | sort | uniq -c | sort -nr | awk '{print $2, $1}'
]$ awk '{for(i=1;i<=NF;i++) a[$i]++} END {for(k in a) print k,a[k]}' words.txt | sort -nrk 2
================================================================================
