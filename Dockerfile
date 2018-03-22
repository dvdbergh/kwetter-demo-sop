FROM payara/server-full
COPY target/kwetter.war /opt/payara41/deployments
EXPOSE 8080
EXPOSE 4848
