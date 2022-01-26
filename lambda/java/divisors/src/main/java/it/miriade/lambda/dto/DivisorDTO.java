package it.miriade.lambda.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@RegisterForReflection
public class DivisorDTO {
    public List<Long> divisors;
}
