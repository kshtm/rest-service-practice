package app.configuration;

import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

import java.util.Objects;

public class AppPropertySourcesPropertyResolver extends PropertySourcesPropertyResolver {

  public AppPropertySourcesPropertyResolver(PropertySources propertySources) {
    super(propertySources);
  }

  @Override
  public String resolvePlaceholders(String text) {
    String resolvedText = super.resolvePlaceholders(text);
    return decode(resolvedText);
  }

  @Override
  public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
    String resolvedText = super.resolveRequiredPlaceholders(text);
    return decode(resolvedText);
  }

  private String decode(String text) {
    if (Objects.isNull(text)) return null;
    if (text.startsWith("smth")) {
      System.out.println(" ");
      return "new";
    }
    return text;
  }
}