package app.configuration;

import app.service.GreetingService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
public class MyBPP implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GreetingService) {
            try {
                Field value = bean.getClass().getField("value");
                ReflectionUtils.setField(value, bean, 1000);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return bean;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
