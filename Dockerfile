FROM maven
MAINTAINER dbd039<czh9919@outlook.com>

ENV CODE /code
ENV WORK /code/work
RUN mkdir -p $CODE \
    && mkdir -p $WORK

WORKDIR $WORK

RUN mkdir codeT
COPY ./springboot-demo codeT
RUN cd codeT && mvn compile clean verify
EXPOSE 9033
CMD cd codeT && mvn spring-boot:run
