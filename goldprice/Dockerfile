FROM maven:3.8.4-openjdk-11

ARG TWILIO_ACC
ARG TWILIO_TOKEN


ENV TWILIO_ACCOUNT_SID = ${TWILIO_ACC}
ENV TWILIO_AUTH_TOKEN = ${TWILIO_TOKEN}

WORKDIR /app

COPY . .

#CMD mvn clean install -Dremote=true -Dtwilio_account=${TWILIO_ACC} -Dtwilio_token=${TWILIO_TOKEN}


CMD ["mvn","test","-Dremote=true"]