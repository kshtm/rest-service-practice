package app.service.impl;

import app.configuration.AspectAnnotation;
import app.dao.GreetingDao;
import app.model.Greeting;
import app.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GreetingServiceImpl implements GreetingService {

    public int value = 0;

    GreetingDao greetingDao;

    @Autowired
    public GreetingServiceImpl(GreetingDao greetingDao) {
        this.greetingDao = greetingDao;
    }

//    @Lookup
//    public ProtoService getProtoService() {
//        return new ProtoService();
//    }

    @Override
    public Greeting save(String content) {
//        getProtoService().printInt();
        return greetingDao.save(new Greeting(content));
    }

}
