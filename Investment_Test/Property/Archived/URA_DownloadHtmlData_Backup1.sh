#!/bin/bash

set -x

# DATA_TYPE: [transaction, resiRental]
DATA_TYPE=$1
# PROPERTY_TYPE: [ac, ec]
PROPERTY_TYPE=$2
# FROM_PERIOD / TO_PERIOD: Format like "JAN-2020"
FROM_PERIOD=$3
TO_PERIOD=$4
# POSTAL_DISTRICT_LIST: A list of postal codes
POSTAL_DISTRICT_LIST=$5
OUTPUT_FILE=$6

echo "DATA_TYPE = "$DATA_TYPE
echo "PROPERTY_TYPE = "$PROPERTY_TYPE
echo "FROM_PERIOD = "$FROM_PERIOD
echo "TO_PERIOD = "$TO_PERIOD
echo "POSTAL_DISTRICT_LIST = "$POSTAL_DISTRICT_LIST
echo "OUTPUT_FILE = "$OUTPUT_FILE

FORM_DATA_POSTAL_LIST=""
for postal_code in ${POSTAL_DISTRICT_LIST}
do
	FORM_DATA_POSTAL_LIST=${FORM_DATA_POSTAL_LIST}"&selectedPostalDistricts1=${postal_code}"
done
echo "FORM_DATA_POSTAL_LIST = "$FORM_DATA_POSTAL_LIST

FORM_DATA_FULL="submissionType=pd&selectedFromPeriodProjectName=${FROM_PERIOD}&selectedToPeriodProjectName=${TO_PERIOD}&__multiselect_selectedProjects1=&selectedFromPeriodPostalDistrict=${FROM_PERIOD}&selectedToPeriodPostalDistrict=${TO_PERIOD}&propertyType=${PROPERTY_TYPE}&saleTypePD=3&postalDistrictList=28${FORM_DATA_POSTAL_LIST}&__multiselect_selectedPostalDistricts1="
echo "FORM_DATA_FULL = "$FORM_DATA_FULL

JSESSIONID=$(curl -sSI "https://www.ura.gov.sg/realEstateIIWeb/${DATA_TYPE}/search.action" --compressed | grep JSESSIONID | cut -d' ' -f2 | cut -d= -f2 | tr -d ';')
echo "JSESSIONID = "$JSESSIONID
# JSESSIONID=${JSESSIONID//!/\\u0021}
# echo "JSESSIONID = "$ACTION_URL

ACTION_URL="https://www.ura.gov.sg/realEstateIIWeb/${DATA_TYPE}/submitSearch.action;jsessionid=${JSESSIONID}"
echo "ACTION_URL = "$ACTION_URL
# ACTION_URL=${ACTION_URL//!/\\u0021}
# echo "ACTION_URL = "$ACTION_URL

ACTION_COOKIE="Cookie: JSESSIONID=${JSESSIONID}"
echo "ACTION_COOKIE = "$ACTION_COOKIE

ACTION_COMMAND="curl -sSv '${ACTION_URL}' -H 'Cookie: JSESSIONID=${JSESSIONID}' -H 'Content-Type: application/x-www-form-urlencoded' -d '${FORM_DATA_FULL}' --compressed -o '${OUTPUT_FILE}' --trace '${OUTPUT_FILE}.log'"
echo "ACTION_COMMAND = "$ACTION_COMMAND

# eval $ACTION_COMMAND
# curl -sSv -H "Cookie: JSESSIONID=${JSESSIONID}" -H 'Content-Type: application/x-www-form-urlencoded' -d ${FORM_DATA_FULL} --compressed -o ${OUTPUT_FILE} --trace ${OUTPUT_FILE}.log "${ACTION_URL}" 

# curl -sSvG "https://www.ura.gov.sg/realEstateIIWeb/${DATA_TYPE}/submitSearch.action" --data-urlencode ";jsessionid=${JSESSIONID}" -H ${ACTION_COOKIE} -H 'Content-Type: application/x-www-form-urlencoded' -d ${FORM_DATA_FULL} --compressed -o ${OUTPUT_FILE} --trace ${OUTPUT_FILE}.log

echo "#!/bin/bash" > URA_Test.sh
echo >> URA_Test.sh
# echo "set -x" >> URA_Test.sh
echo >> URA_Test.sh
echo $ACTION_COMMAND >> URA_Test.sh
echo >> URA_Test.sh
