FROM ${baseimage.name}:${baseimage.version}

COPY finalJar/* /myapp/
COPY jars /myapp/jars/
COPY ReadMe.txt /myapp/

RUN set -x && \
    ls -l / && \
    ls -l /myapp && \
    ls -l /myapp/jars

ENTRYPOINT ["/bin/bash"]
