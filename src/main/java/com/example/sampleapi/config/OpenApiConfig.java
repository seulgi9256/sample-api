package com.example.sampleapi.config;


import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Sample-Api 명세서", description = "Sample-Api 명세서 설명 "))
@Configuration
public class OpenApiConfig {
  @Profile({"local", "dev"})
  @Bean
  public GroupedOpenApi demoApi() {
    String[] paths = {"/sample-api/**"};
    return GroupedOpenApi.builder().group("sample-api").pathsToMatch(paths).build();
  }
}
