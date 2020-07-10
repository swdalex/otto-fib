package com.swdalex.otto.fib;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestOperationsConfig {

  @Value("${server.hostname}")
  private String hostname;
  @Value("${server.port}")
  private String port;

  @Bean
  RestOperations restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setUriTemplateHandler(new RootUriTemplateHandler("http://" + hostname + ":" + port));
    return restTemplate;
  }
}
