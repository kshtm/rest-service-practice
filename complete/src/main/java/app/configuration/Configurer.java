package app.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.ConfigurablePropertyResolver;

@Configuration
public class Configurer {

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(ConfigurableEnvironment environment) {
    PropertySourcesPlaceholderConfigurer res = new MyPropertySourcesPlaceholderConfigurer();
    res.setEnvironment(environment);
    res.setPropertySources(environment.getPropertySources());

    return res;
  }


}
