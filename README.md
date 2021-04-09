# Cloud Benchmark

Repository per fare un po' di test su backend sviluppati con diverse tecnologie cloud.

## Ambienti

Sono stati testati i segenti ambienti e linguaggi

- **python3.8 on Lambda**
- **quarkus on Lambda** come Jar
- **quarkus on Lambda** come eseguibile nativo
- **quarkus on EC2** come eseguibile nativo su t2.micro
- **quarkus on EC2** come eseguibile nativo su t4g.micro (ARM)
- **go on EC2** su una t2.micro
- **go on EC2** su una t4g.micro (ARM)

## Caveats

- per interrogare le EC2 con TLS bisogna passare per l'api gateway

