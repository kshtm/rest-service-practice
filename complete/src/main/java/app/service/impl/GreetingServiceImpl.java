package app.service.impl;

import app.dao.GreetingDao;
import app.model.Greeting;
import app.service.GreetingService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Primary
public class GreetingServiceImpl implements GreetingService {

  private static final String topicName = "MyTopic";

  private KafkaTemplate<String, String> kafkaTemplate;

  GreetingDao greetingDao;

  @Autowired
  public GreetingServiceImpl(GreetingDao greetingDao, KafkaTemplate<String, String> kafkaTemplate) {
    this.greetingDao = greetingDao;
    this.kafkaTemplate = kafkaTemplate;
  }

  @Lookup
  public ProtoService getProtoService() {
    return null;
  }

  @Override
  public Greeting save(String content) {
//    System.err.println("Hello from Look up " + getProtoService().field);
    sendMessage("Saving " + content);
    return greetingDao.save(new Greeting(content));
  }

  public void sendMessage(String msg) {
    kafkaTemplate.send(topicName, "Saving", msg);
  }

  @Override
  public Greeting findById(long id) {
    return greetingDao.getOne(id);
  }

  @Override
  public List<Greeting> findAll() {
    return greetingDao.findAll();
  }

  @Override
  public Greeting findByContent(String name) {
    Optional<Greeting> byName = greetingDao.findByContent(name);
    return byName.get();
  }

}
