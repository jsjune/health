FROM openjdk:17-alpine

VOLUME /tmp

COPY build/libs/health-0.0.1-SNAPSHOT.jar app.jar

# 환경 변수 설정
ENV SPRING_PROFILES_ACTIVE=production
ENV DATABASE_URL=jdbc:mysql://mysql-master-service:3306/battingGround
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=1234

ENTRYPOINT ["java", "-jar", "app.jar"]
