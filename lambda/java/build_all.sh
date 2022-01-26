#!/bin/sh

set -xeu

# divisors
cd divisors
./mvnw clean package
mv target/function.zip ../divisors-jvm.zip
./mvnw clean package -Pnative
mv target/function.zip ../divisors-native.zip
cd ..

# greetings
cd greetings
./mvnw clean package
mv target/function.zip ../greetings-jvm.zip
./mvnw clean package -Pnative
mv target/function.zip ../greetings-native.zip
cd ..
