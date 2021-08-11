#!/bin/bash

# DATA_TYPE: [transaction, resiRental]
DATA_TYPE=$1
# PROPERTY_TYPE: [ac, ec, lp, sl]
# "sl" only for transaction.
PROPERTY_TYPE=$2
# FROM_PERIOD / TO_PERIOD: Format like "JAN-2020"
FROM_PERIOD=$3
TO_PERIOD=$4
OUTPUT_FOLDER=$5
OUTPUT_FILE_PREFIX=$6

echo "------------------------------------------------------------"
echo "DATA_TYPE = "$DATA_TYPE
echo "PROPERTY_TYPE = "$PROPERTY_TYPE
echo "FROM_PERIOD = "$FROM_PERIOD
echo "TO_PERIOD = "$TO_PERIOD
echo "OUTPUT_FOLDER = "$OUTPUT_FOLDER
echo "OUTPUT_FILE_PREFIX = "$OUTPUT_FILE_PREFIX

if [ "$DATA_TYPE" != "transaction" ]; then
	if [ "$DATA_TYPE" != "resiRental" ]; then
		echo "Error: DATA_TYPE wrong!"
		exit -1
	fi
fi

if [ "$PROPERTY_TYPE" != "ac" ]; then
	if [ "$PROPERTY_TYPE" != "ec" ]; then
		if [ "$PROPERTY_TYPE" != "lp" ]; then
			if [ "$PROPERTY_TYPE" != "sl" ]; then
				echo "Error: PROPERTY_TYPE wrong!"
				exit -1
			fi
		fi
	fi
fi

if [ "$DATA_TYPE" == "transaction" ]; then
	FROM_PERIOD=${FROM_PERIOD// /+}
	TO_PERIOD=${TO_PERIOD// /+}
else
	FROM_PERIOD=${FROM_PERIOD// /-}
	TO_PERIOD=${TO_PERIOD// /-}
fi

echo "FROM_PERIOD = "$FROM_PERIOD
echo "TO_PERIOD = "$TO_PERIOD

make_form_data_postal_list()
{
	L_POSTAL_DISTRICT_LIST=$*
	L_FORM_DATA_POSTAL_LIST=""
	if [ $DATA_TYPE == "transaction" ]; then
		for postal_code in ${L_POSTAL_DISTRICT_LIST}
		do
			L_FORM_DATA_POSTAL_LIST=${L_FORM_DATA_POSTAL_LIST}"&selectedPostalDistricts1=${postal_code}"
		done
	else
		for postal_code in ${L_POSTAL_DISTRICT_LIST}
		do
			L_FORM_DATA_POSTAL_LIST=${L_FORM_DATA_POSTAL_LIST}"&selectedPostalDistricts=${postal_code}"
		done
	fi
	echo  $L_FORM_DATA_POSTAL_LIST
}

make_form_data_full()
{
	L_FORM_DATA_POSTAL_LIST=$1
	if [ $DATA_TYPE == "transaction" ]; then
		L_FORM_DATA_FULL="submissionType=pd&selectedFromPeriodProjectName=${FROM_PERIOD}&selectedToPeriodProjectName=${TO_PERIOD}&__multiselect_selectedProjects1=&selectedFromPeriodPostalDistrict=${FROM_PERIOD}&selectedToPeriodPostalDistrict=${TO_PERIOD}&propertyType=${PROPERTY_TYPE}&saleTypePD=1&saleTypePD=2&saleTypePD=3&postalDistrictList=28${L_FORM_DATA_POSTAL_LIST}&__multiselect_selectedPostalDistricts1="
	else
		L_FORM_DATA_FULL="submissionType=pd&from_Date_Prj=${FROM_PERIOD}&to_Date_Prj=${TO_PERIOD}&__multiselect_selectedProjects=&from_Date=${FROM_PERIOD}&to_Date=${TO_PERIOD}&propertyType=${PROPERTY_TYPE}&postalDistrictList=28${L_FORM_DATA_POSTAL_LIST}&__multiselect_selectedPostalDistricts="
	fi
	echo  $L_FORM_DATA_FULL
}

echo "#!/bin/bash" > ${OUTPUT_FOLDER}/${OUTPUT_FILE_PREFIX}.sh
echo >> ${OUTPUT_FOLDER}/${OUTPUT_FILE_PREFIX}.sh
N=1
for postal_code_list in "01 02 03 04 05" "06 07 08 09 10" "11 12 13 14 15" "16 17 18 19 20" "21 22 23 24 25" "26 27 28"
do
	JSESSIONID=$(curl -sSI "https://www.ura.gov.sg/realEstateIIWeb/${DATA_TYPE}/search.action" --compressed | grep JSESSIONID | cut -d' ' -f2 | cut -d= -f2 | tr -d ';')
	echo $N": JSESSIONID = "$JSESSIONID

	ACTION_URL_SQF="https://www.ura.gov.sg/realEstateIIWeb/${DATA_TYPE}/submitSearch.action"
	echo $N": ACTION_URL_SQF = "$ACTION_URL_SQF
	ACTION_URL_SQM="https://www.ura.gov.sg/realEstateIIWeb/${DATA_TYPE}/changeDisplayUnit.action"
	echo $N": ACTION_URL_SQM = "$ACTION_URL_SQM

	FORM_DATA_POSTAL_LIST=$(make_form_data_postal_list ${postal_code_list})
	echo $N": FORM_DATA_POSTAL_LIST = "$FORM_DATA_POSTAL_LIST
	FORM_DATA_FULL=$(make_form_data_full ${FORM_DATA_POSTAL_LIST})
	echo $N": FORM_DATA_FULL = "$FORM_DATA_FULL

    # Ignore SquareFeet data.
	ACTION_COMMAND_SQF="curl -sS '${ACTION_URL_SQF}' -H 'Cookie: JSESSIONID=${JSESSIONID}' -H 'Content-Type: application/x-www-form-urlencoded' -d '${FORM_DATA_FULL}' --compressed > /dev/null"
	echo $N": ACTION_COMMAND_SQF = "$ACTION_COMMAND_SQF
	echo $ACTION_COMMAND_SQF >> ${OUTPUT_FOLDER}/${OUTPUT_FILE_PREFIX}.sh
	echo >> ${OUTPUT_FOLDER}/${OUTPUT_FILE_PREFIX}.sh

    # Save SquareMeter data.
	ACTION_COMMAND_SQM="curl -sS '${ACTION_URL_SQM}' -H 'Cookie: JSESSIONID=${JSESSIONID}' -H 'Content-Type: application/x-www-form-urlencoded' -d 'sortBy=' --compressed -o '${OUTPUT_FILE_PREFIX}${N}.html' &"
	echo $N": ACTION_COMMAND_SQM = "$ACTION_COMMAND_SQM
	echo $ACTION_COMMAND_SQM >> ${OUTPUT_FOLDER}/${OUTPUT_FILE_PREFIX}.sh
	echo >> ${OUTPUT_FOLDER}/${OUTPUT_FILE_PREFIX}.sh

	N=$(($N + 1))
done

echo "------------------------------------------------------------"
