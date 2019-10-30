package app.configuration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Configurer {

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(ConfigurableEnvironment environment) {
    PropertySourcesPlaceholderConfigurer res = new MyPropertySourcesPlaceholderConfigurer();
    res.setEnvironment(environment);
    res.setPropertySources(environment.getPropertySources());

    return res;
  }

//  @Bean
//  public KafkaAdmin admin() {
//    Map<String, Object> configs = new HashMap<>();
//    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////            StringUtils.arrayToCommaDelimitedString(kafkaEmbedded().getBrokerAddresses()));
//    return new KafkaAdmin(configs);
//  }

  @Bean
  public AdminClient adminClient() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    return AdminClient.create(configs);
  }
}
