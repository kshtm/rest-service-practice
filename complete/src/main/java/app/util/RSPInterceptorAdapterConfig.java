package app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RSPInterceptorAdapterConfig implements WebMvcConfigurer {

    @Autowired
    RSPInterceptorAdapter rspInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rspInterceptorAdapter)
                .addPathPatterns("/**");
    }
}