FROM maven:3.9.6-eclipse-temurin-17 AS builder
RUN mkdir /receipt-processor
COPY ./pom.xml /receipt-processor
COPY ./src /receipt-processor/src
WORKDIR /receipt-processor
RUN mvn clean package --quiet

FROM eclipse-temurin:17.0.12_7-jre-noble AS prod
RUN mkdir /opt/receipt-processor
RUN groupadd -r javauser && useradd --no-log-init -r -g javauser javauser
COPY --from=builder /receipt-processor/target/fetch-rewards-1.0.0.jar /opt/receipt-processor/application.jar
WORKDIR /opt/receipt-processor
RUN chown -R javauser:javauser /opt/receipt-processor
EXPOSE 8080
USER javauser
CMD ["java", "-jar", "/opt/receipt-processor/application.jar"]

FROM prod AS debug
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
EXPOSE 5005