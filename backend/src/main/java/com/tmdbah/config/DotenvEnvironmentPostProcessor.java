package com.tmdbah.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.EnvironmentPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

/**
 * Loads key=value pairs from a .env file in the working directory into the environment,
 * after the real system environment so actual env vars still take precedence.
 * Spring Boot 4 relocated ConfigurableBootstrapContext, which breaks
 * SpringApplicationRunListener-based dotenv libraries (e.g. spring-dotenv); this
 * EnvironmentPostProcessor hook is unaffected by that change.
 */
public class DotenvEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String PROPERTY_SOURCE_NAME = "dotenvFile";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Path envFile = Path.of(System.getProperty("user.dir"), ".env");
        if (!Files.isRegularFile(envFile)) {
            return;
        }

        Map<String, Object> values = parse(envFile);
        if (values.isEmpty()) {
            return;
        }

        environment.getPropertySources()
                .addAfter(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
                        new MapPropertySource(PROPERTY_SOURCE_NAME, values));
    }

    private Map<String, Object> parse(Path envFile) {
        Map<String, Object> values = new LinkedHashMap<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(envFile);
        } catch (IOException e) {
            return values;
        }

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }

            int separator = trimmed.indexOf('=');
            if (separator <= 0) {
                continue;
            }

            String key = trimmed.substring(0, separator).trim();
            String value = trimmed.substring(separator + 1).trim();
            if ((value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2)
                    || (value.startsWith("'") && value.endsWith("'") && value.length() >= 2)) {
                value = value.substring(1, value.length() - 1);
            }

            values.put(key, value);
        }

        return values;
    }
}
