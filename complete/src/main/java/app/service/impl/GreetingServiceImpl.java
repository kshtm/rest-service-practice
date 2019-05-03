package app.service.impl;

import app.dao.GreetingDao;
import app.model.Greeting;
import app.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class GreetingServiceImpl implements GreetingService {


  GreetingDao greetingDao;

  @Autowired
  public GreetingServiceImpl(GreetingDao greetingDao) {
    this.greetingDao = greetingDao;
  }

  @Override
  public Greeting save(String content) { ;
    return greetingDao.save(new Greeting(content));
  }

  @Override
  public Greeting findById(long id) {
    return greetingDao.getOne(id);
  }

  @Override
  public List<Greeting> findAll() {
    return greetingDao.findAll();
  }

}
