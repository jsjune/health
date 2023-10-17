FROM openjdk:17-alpine

VOLUME /tmp

COPY build/libs/health-0.0.1-SNAPSHOT.jar app.jar

# 환경 변수 설정
ENV SPRING_PROFILES_ACTIVE=production
ENV SPRING_DATASOURCE_MASTER_URL=jdbc:mysql://mysql-master-service:3306/battingGround?allowPublicKeyRetrieval=true&useSSL=false
ENV SPRING_DATASOURCE_MASTER_USERNAME=root
ENV SPRING_DATASOURCE_MASTER_PASSWORD=1234
ENV SPRING_DATASOURCE_SLAVE_URL=jdbc:mysql://mysql-slave-service:3306/battingGround?allowPublicKeyRetrieval=true&useSSL=false
ENV SPRING_DATASOURCE_SLAVE_USERNAME=root
ENV SPRING_DATASOURCE_SLAVE_PASSWORD=1234

ENTRYPOINT ["java", "-jar", "app.jar"]
