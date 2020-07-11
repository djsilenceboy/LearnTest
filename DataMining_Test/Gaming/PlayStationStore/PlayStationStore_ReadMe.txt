================================================================================
PlayStation Store Game List
------------------------------------------------------------
(For Singapore)
https://store.playstation.com/en-sg/grid/STORE-MSF86012-GAMESALL/1?direction=desc&gameContentType=bundles&smcid=pdc%3Aen-sg%3Aprimary%2520nav%3Amsg-shop%3Aps-store&sort=release_date
================================================================================

================================================================================
Rest API
------------------------------------------------------------
Request URL: https://store.playstation.com/valkyrie-api/en/SG/<AreaCode>/container/STORE-MSF86012-GAMESALL?<QueryParams>
Request Method: GET

AreaCode: [19, 999] for SG eng games
          [1, 2] for SG jpn games
================================================================================
Query parameters
------------------------------------------------------------
Note that, use comma for a list, encoded as "%2C".
----------------------------------------
Required (but could optional):

size: [0, 99]. Page size. Set 0 to only get available game numbers. Default value is "30".
start: [0, ). Start position of current page. Default value is "0".
----------------------------------------
Optional:

bucket: [games]. Default value is "games".
direction: [asc, desc]. Default value is "desc".
game_content_type: [games, bundles]. A list separated by comma. Default value is "games".
genre: [sports, shooter, role_playing_games, (and more)]. A list separated by comma. Default value is all.
platform: [ps4, ps3, psp, vita]. A list separated by comma. Default value is all.
price: [0-0, 1-789, 790-1589, 1590-3189, 3190-7989, 7990-*]. A list of price in cents of SGD, separated by comma. Default value is all.
release_date: [coming_soon, last_7_days, last_30_days]. A list separated by comma. Default value is all.
sort: [name, price, release_date]. Default value is "release_date".
================================================================================
Full Sample

curl 'https://store.playstation.com/valkyrie-api/en/SG/19/container/STORE-MSF86012-GAMESALL?sort=price&direction=asc&price=1590-3189&platform=ps4&size=30&bucket=games&start=0' -H 'Referer: https://store.playstation.com/en-sg/grid/STORE-MSF86012-GAMESALL/2?direction=asc&platform=ps4&price=1590-3189&smcid=pdc%3Aen-sg%3Aprimary%2520nav%3Amsg-shop%3Aps-store&sort=price' -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36' --compressed
------------------------------------------------------------
Minimum required sample

curl 'https://store.playstation.com/valkyrie-api/en/SG/19/container/STORE-MSF86012-GAMESALL?size=30&bucket=games&start=0' --compressed
------------------------------------------------------------
Parameters sample

sort=price&direction=asc&price=1590-3189%2C0-0%2C1-789%2C790-1589%2C3190-7989%2C7990-*&platform=ps4%2Cps3%2Cpsp%2Cvita&size=30&bucket=games&start=0
------------------------------------------------------------
Only get available game numbers.

curl -sS 'https://store.playstation.com/valkyrie-api/en/SG/19/container/STORE-MSF86012-GAMESALL?size=0' --compressed
================================================================================

================================================================================
Test
------------------------------------------------------------
curl -sS 'https://store.playstation.com/valkyrie-api/en/SG/19/container/STORE-MSF86012-GAMESALL' --compressed -o PSS_Sample.json

curl -sS 'https://store.playstation.com/valkyrie-api/en/SG/19/container/STORE-MSF86012-GAMESALL?size=0' --compressed -o PSS_Sample.json
curl -sS 'https://store.playstation.com/valkyrie-api/en/SG/19/container/STORE-MSF86012-GAMESALL?size=10' --compressed -o PSS_Sample.json

curl -sS 'https://store.playstation.com/valkyrie-api/en/SG/19/container/STORE-MSF86012-GAMESALL?game_content_type=games%2Cbundles&sort=name&direction=asc&size=99&start=0' --compressed -o PSS_Sample.json
================================================================================

================================================================================
Download raw data
================================================================================
Run:

PlayStationStore_RetrieveSummaryData.bat
PlayStationStore_RetrieveGameList.bat
------------------------------------------------------------
Generated files:

.\RawData\PlayStationStore_Summary.csv
.\RawData\PlayStationStore_RetrieveGameList.csv
================================================================================
