#!/bin/bash

CURRENT_FOLDER=$(pwd)

for Year in {2017..2021}
do
	${CURRENT_FOLDER}/URA_DownloadHtmlData_Main.sh $Year 01 JAN $Year 12 DEC
done
