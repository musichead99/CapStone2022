package com.musichead.capstone2022_backend;

import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDateTime;

@Configuration
public class GraphqlConfig {

    @Bean
    public RuntimeWiringConfigurer configurer() {
        GraphQLScalarType scalarType = GraphQLScalarType.newScalar()
                .name("LocalDateTime")
                .description("Java 8 LocalDateTime Scalar")
                .coercing(new Coercing<LocalDateTime, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        LocalDateTime outputLocalDateTime = (LocalDateTime)dataFetcherResult;
                        return outputLocalDateTime.toString();
                    }

                    @Override
                    public LocalDateTime parseValue(Object input) throws CoercingParseValueException {
                        if(input != null) {
                            String inputLocalDateTime = (String) input;
                            return LocalDateTime.parse(inputLocalDateTime);
                        }
                        else {
                            throw new CoercingParseValueException("Expected String Object");
                        }
                    }

                    @Override
                    public LocalDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
                        if(input != null) {
                            String inputLocalDateTime = (String) input;
                            return LocalDateTime.parse(inputLocalDateTime);
                        }
                        else {
                            throw new CoercingParseValueException("Expected String Object");
                        }
                    }
                })
                .build();
        return (builder) -> {builder.scalar(scalarType).build();};
    }
}
