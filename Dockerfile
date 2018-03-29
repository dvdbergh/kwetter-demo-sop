FROM payara/server-full
COPY target/kwetter.war /opt/payara5/glassfish/domains/domain1/autodeploy
EXPOSE 4848 8009 8080 8181
