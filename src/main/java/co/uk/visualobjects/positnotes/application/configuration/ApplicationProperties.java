package co.uk.visualobjects.positnotes.application.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


/**
 * A bean that matches the application.yml config file.
 * Autowured into application to provide simple access to configuration.
 */
@Component
@ConfigurationProperties
public class ApplicationProperties {

    public static class Application {

        @NotNull
        private DataSource datasource;
    }

}