package com.musichead.capstone2022_backend;

import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlConfig {

    @Bean
    public RuntimeWiringConfigurer configurer() {
        GraphQLScalarType scalarType = ApolloScalars.Upload;
        return (builder) -> {builder.scalar(scalarType).build();};
    }
}
