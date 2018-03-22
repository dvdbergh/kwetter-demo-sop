FROM payara/server-full
COPY target/kwetter.war /opt/payara41/deployments
