FROM java:8


RUN apt-get update && \
    apt-get install -y build-essential make pkg-config libtool autoconf automake curl

RUN apt-get install uuid-dev -y

RUN cd /opt && \
    curl https://archive.org/download/zeromq_2.2.0/zeromq-2.2.0.tar.gz -L -o /opt/zeromq-2.2.0.tar.gz && \
    tar -zxf zeromq-2.2.0.tar.gz && \
    rm zeromq-2.2.0.tar.gz && \
    cd /opt/zeromq-2.2.0 && \
    ./configure && \
    make && \
    make install && \
    ldconfig && \
    cd /opt && \
    rm -rf zeromq-2.2.0

RUN ln -s /usr/bin/libtoolize /usr/bin/libtool

RUN apt-get install execstack -y --force-yes