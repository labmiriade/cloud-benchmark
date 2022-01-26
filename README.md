# Cloud Benchmark

Repository per fare un po' di test su backend sviluppati con diverse tecnologie cloud.

## Ambienti

Sono stati testati i segenti ambienti e linguaggi

- **python3.8 on Lambda**
- **quarkus on Lambda** come Jar
- **quarkus on Lambda** come eseguibile nativo
- **quarkus on EC2** come eseguibile nativo su t2.micro
- **quarkus on EC2** come eseguibile nativo su t4g.micro (ARM)
- **go on Lambda**
- **go on EC2** su una t2.micro
- **go on EC2** su una t4g.micro (ARM)

## Caveats

- per interrogare le EC2 con TLS bisogna passare per l'api gateway
- prima di deployare è necessario lanciare lo script `lambda/java/build-all.sh`, altrimenti SAM fallirà

## API Prefixes

L'url è composto da:

```
https://zhgqmwxzz4.execute-api.eu-central-1.amazonaws.com/api/{base}/{openapi path}
```

| **BASE** | python 3.8 | quarkus + JVM | quarkus           | go 1.6       |
| -------- | ---------- | ------------- | ----------------- | ------------ |
| lambda   | python38/  | quarkus/java/ | quarkus/native/   | go/          |
| t2micro  | -          | -             | t2micro/quarkus/  | t2micro/go/  |
| t4gmicro | -          | -             | t4gmicro/quarkus/ | t4gmicro/go/ |
