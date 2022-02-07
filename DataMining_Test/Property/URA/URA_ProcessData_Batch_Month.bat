
FOR %%Y IN (2017 2018 2019 2020 2021) DO (
  FOR %%M IN (01 02 03 04 05 06 07 08 09 10 11 12) DO URA_ProcessData_Main.bat %%Y %%M %%Y %%M
)
