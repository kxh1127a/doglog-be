# OpenJDK 17 기반 이미지 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# Gradle Wrapper 복사
COPY gradlew gradlew
COPY gradle gradle

# Gradle Wrapper 실행 권한 부여
RUN chmod +x gradlew

# 프로젝트 소스 코드 복사
COPY . .

# Gradle 빌드 실행
RUN ./gradlew clean build

# 애플리케이션 실행
CMD ["java", "-jar", "build/libs/doglog-be-0.0.1-SNAPSHOT.jar"]
