#!/bin/bash
#
# Purpose:
#   EE: Pull images.
#
# Usage:
#   To be uploaded and run on remote host by Ansible:
#   ThisScript <HostID> <ExtraParameters>
#
#   HostID:
#     A string defined in Ansible host file.
#   ExtraParameters:
#     A list of parameters separated by comma.
#     DtrUserName,DtrPassword,DtrEmail,DtrDevSubPath,DtrReleaseVersion
#
#     DtrUserName,DtrPassword,DtrEmail:
#       Parameters (User name, password and Email) to login default DTR server "dtr.sample.com".
#       For example, "dev-user,devpassw0rd,docker@docker.com".
#     DtrDevSubPath:
#       Part of sub path to release location.
#       Empty means "release". "-dev" means "release-dev".
#     DtrReleaseVersion:
#       Release version. For example, "ivt_14.5.20170422-1012.745".
#
# exit: 0
#       1 - Cannot login DTR.
#       2 - Cannot pull image(s).
#
# Update log: (date / version / author : comments)
# 2017-04-27 / release / Du Jiang : Creation

HostID=$1
ExtraParameters=$2
IFS=, read DtrUserName DtrPassword DtrEmail DtrDevSubPath DtrReleaseVersion <<< "$ExtraParameters"

printf '%.0s-' {1..80}; echo
echo "Date: "`date +"%F %H:%M:%S %Z%z"`
echo "Host ID: "$HostID
echo -n "Hostname and IP: "
echo -n `hostname`
echo -n ", "
# IP.
echo `hostname -I | awk '{print $1}'`

printf '%.0s-' {1..60}; echo

EXIT_CODE=0
DtrServer=dtr.sample.com
DtrPathPrefix="dtr.sample/release"$DtrDevSubPath
DockerImageList=(image1 image2 image3)

echo "DTR server: "$DtrServer
echo "DTR login user name: "$DtrUserName
echo "DTR login password: "$DtrPassword
echo "DTR login email: "$DtrEmail
echo "DTR path prefix: "$DtrPathPrefix
echo "DTR release version: "$DtrReleaseVersion
echo "Docker image list: "${DockerImageList[@]}
printf '%.0s-' {1..60}; echo

if [ $EXIT_CODE -eq 0 ]; then
	echo "Login DTR..."
	docker login -u $DtrUserName -p "$DtrPassword" -e "$DtrEmail" $DtrServer > /dev/null 2>&1
	if [ $? -eq 0 ]; then
		echo "Login succeeded."
	else
		echo "Login failed!"
		EXIT_CODE=1
	fi
fi

if [ $EXIT_CODE -eq 0 ]; then
	printf '%.0s-' {1..60}; echo
	for ImageName in ${DockerImageList[@]}
	do
		DtrFullReleasePath=$DtrPathPrefix/$ImageName:$DtrReleaseVersion
		echo "DTR full release path: "$DtrFullReleasePath

		# docker pull "$DtrFullReleasePath" > /dev/null 2>&1
		if [ $? -eq 0 ]; then
			echo "Pull image succeeded."
		else
			echo "Pull image failed!"
			EXIT_CODE=2
		fi
		printf '%.0s-' {1..40}; echo
	done
fi

printf '%.0s-' {1..60}; echo
echo "Check images for this release..."
docker images | grep "$DtrReleaseVersion"

printf '%.0s-' {1..60}; echo
echo "Logout DTR..."
docker logout $DtrServer > /dev/null 2>&1

printf '%.0s-' {1..60}; echo
echo "Date: "`date +"%F %H:%M:%S %Z%z"`
printf '%.0s=' {1..80}; echo
# Not return the exit code. The error message will not be saved in separated output file.
# exit $EXIT_CODE
