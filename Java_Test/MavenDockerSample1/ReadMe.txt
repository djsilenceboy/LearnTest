================================================================================
For docker:build,

The maven-docker-plugin will remote access Docker Engine "http://192.168.10.17:2375".
Pull the base image, and create new image based on Dockerfile.
------------------------------------------------------------
For docker:push,

The maven-docker-plugin will remote access Docker Engine "http://192.168.10.17:2375".
Push the new image to anotehr Docker Registry "docker.djsilenceboy.com:5000".
================================================================================

================================================================================
To enable remote access of Docker Engine.
------------------------------------------------------------
sudo mkdir -p /etc/systemd/system/docker.service.d
sudo vi /etc/systemd/system/docker.service.d/docker.conf
----------------------------------------
[Service]
ExecStart=
ExecStart=/usr/bin/dockerd -H unix://var/run/docker.sock -H tcp://0.0.0.0:2375
----------------------------------------
sudo systemctl daemon-reload
sudo systemctl restart docker
================================================================================
To use host name for Push Registry.
------------------------------------------------------------
Add following host into "C:\Windows\System32\drivers\etc" of develop PC:

192.168.0.16     docker.djsilenceboy.com
================================================================================
To test
------------------------------------------------------------
mvn docker:build docker:push
or
mvn package
================================================================================

================================================================================
Check repo info on repo VM.
------------------------------------------------------------
List all repositories.

curl -k -X GET https://localhost:5000/v2/_catalog
------------------------------------------------------------
List all tags for a repository.

curl -i -k -X GET https://localhost:5000/v2/<RepositoryName>/tags/list

curl -i -k -X GET https://localhost:5000/v2/maven-docker-sample/tags/list
------------------------------------------------------------
List manifest of a tag of a repository.

curl -i -k -X GET https://localhost:5000/v2/<RepositoryName>/manifests/<Tag>

curl -i -k -X GET https://localhost:5000/v2/maven-docker-sample/manifests/1.0.0-SNAPSHOT
----------------------------------------
To get the Digest generated when pushing the image.

curl -i -k -H "Accept: application/vnd.docker.distribution.manifest.v2+json" -X GET https://localhost:5000/v2/<RepositoryName>/manifests/<Tag>

curl -i -k -H "Accept: application/vnd.docker.distribution.manifest.v2+json" -X GET https://localhost:5000/v2/maven-docker-sample/manifests/1.0.0-SNAPSHOT

Docker-Content-Digest: sha256:xxxxx
------------------------------------------------------------
Delete a tag of a repository.

curl -i -k -X DELETE https://localhost:5000/v2/<RepositoryName>/manifests/<Digest>

curl -i -k -X DELETE https://localhost:5000/v2/maven-docker-sample/manifests/<Digest>

<Digest>: The one generated when pushing the image.
================================================================================
