#!/bin/bash

CurrentFolder=$(pwd)
EndYear=$1
EndMonth=$2

# ${CurrentFolder}/URA_DownloadCsvData_Main.sh 2018 06 2018 12
# ${CurrentFolder}/URA_DownloadCsvData_Main.sh 2019 01 2019 12
# ${CurrentFolder}/URA_DownloadCsvData_Main.sh 2020 01 2020 12
# ${CurrentFolder}/URA_DownloadCsvData_Main.sh 2021 01 2021 12
# ${CurrentFolder}/URA_DownloadCsvData_Main.sh 2022 01 2022 12
# ${CurrentFolder}/URA_DownloadCsvData_Main.sh 2023 01 2023 12
# ${CurrentFolder}/URA_DownloadCsvData_Main.sh 2024 01 2024 12
# ${CurrentFolder}/URA_DownloadCsvData_Main.sh 2025 01 2025 12
${CurrentFolder}/URA_DownloadCsvData_Main.sh ${EndYear} 01 ${EndYear} ${EndMonth}
