package com.gutendex.gutendex_catalog.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.gutendex.gutendex_catalog.repository")
public class AppConfig {
}
