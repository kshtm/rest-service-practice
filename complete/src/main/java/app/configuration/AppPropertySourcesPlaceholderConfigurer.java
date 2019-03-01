package app.configuration;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

public class AppPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

    private Environment environment;

    public AppPropertySourcesPlaceholderConfigurer environment(Environment environment) {
        this.environment = environment;
        return this;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}