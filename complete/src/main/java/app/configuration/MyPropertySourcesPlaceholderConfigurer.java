package app.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.Environment;


public class MyPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

  private Environment environment;

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  @Override
  protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                   ConfigurablePropertyResolver propertyResolver) throws BeansException {
    super.processProperties(beanFactoryToProcess,
        (ConfigurablePropertyResolver) new AppPropertySourcesPropertyResolver(((ConfigurableEnvironment) environment).getPropertySources()));
  }
}
