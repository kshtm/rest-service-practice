package app.service.impl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE
////        , proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class ProtoService {

    int field = new Random().nextInt(100);

}
