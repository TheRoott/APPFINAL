package com.reciclacontigo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");
        
        if (databaseUrl != null && databaseUrl.startsWith("postgres://")) {
            try {
                // Parse Render's DATABASE_URL format
                URI dbUri = new URI(databaseUrl);
                
                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String host = dbUri.getHost();
                int port = dbUri.getPort();
                String database = dbUri.getPath().substring(1); // Remove leading slash
                
                // Convert to JDBC URL format
                String jdbcUrl = String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
                
                return DataSourceBuilder
                        .create()
                        .url(jdbcUrl)
                        .username(username)
                        .password(password)
                        .driverClassName("org.postgresql.Driver")
                        .build();
                        
            } catch (URISyntaxException e) {
                throw new RuntimeException("Invalid DATABASE_URL format", e);
            }
        }
        
        // Fallback to default configuration from application.properties
        return DataSourceBuilder
                .create()
                .url(System.getenv().getOrDefault("SPRING_DATASOURCE_URL", "jdbc:postgresql://localhost:5432/recicla_contigo"))
                .username(System.getenv().getOrDefault("DB_USERNAME", "postgres"))
                .password(System.getenv().getOrDefault("DB_PASSWORD", "password"))
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}


