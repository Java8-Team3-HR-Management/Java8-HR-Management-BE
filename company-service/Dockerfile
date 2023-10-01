
FROM amazoncorretto:17.0.8-alpine3.18
#kendi projemizi bu bilgisayarın içine kopyalamak için işlem yapıyoruz
COPY build/libs/company-service-1.0-SNAPSHOT.jar app.jar
#son olarak uygulamamızın çalışması için onu başlatıyoruz
ENTRYPOINT ["java","-jar","/app.jar"]
