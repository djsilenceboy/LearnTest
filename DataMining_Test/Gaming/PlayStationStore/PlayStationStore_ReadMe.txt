================================================================================
PlayStation Store Game List
------------------------------------------------------------
(For Singapore)
https://store.playstation.com/en-sg/grid/STORE-MSF86012-GAMESALL/1?direction=desc&gameContentType=bundles&smcid=pdc%3Aen-sg%3Aprimary%2520nav%3Amsg-shop%3Aps-store&sort=release_date
================================================================================

================================================================================
All steps in one batch
------------------------------------------------------------
Retrieve game list
Retrieve summary data
================================================================================
Update "PlayStationStore_ProcessData_Batch.bat" script with parameters.

Run:

PlayStationStore_ProcessData_Batch.bat
================================================================================
Following are separated steps.
================================================================================

================================================================================
Download raw data
================================================================================
Run:

PlayStationStore_RetrieveGameList.bat 20200712
PlayStationStore_RetrieveSummaryData.bat 20200712
------------------------------------------------------------
Generated files:

.\RawData\PlayStationStore_GameList_20200712.csv
.\RawData\PlayStationStore_Summary_20200712.csv
================================================================================
