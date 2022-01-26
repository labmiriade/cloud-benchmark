1. Assicurati di aver installato e configurato la CLI di AWS
2. Effettua la build:
  - versione java:   `./mvnw package`
  - versione native: `./mvnw package -Pnative`
  
3. Upload del file:
  - versione java:   `LAMBDA_ROLE_ARN="arn:aws:iam::312948075487:role/lambda-ex" target/manage.sh create`
  - versione native: `LAMBDA_ROLE_ARN="arn:aws:iam::312948075487:role/lambda-ex" target/manage.sh native create`
  
Andando a sostituire l'ARN del ruolo della lambda con quello corretto.

