package app.service.impl;

import app.dao.GreetingDao;
import app.model.Greeting;
import app.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    private static final String topicName = "MyTopic";

    private KafkaTemplate<String, String> kafkaTemplate;

    GreetingDao greetingDao;

    @Autowired
    public GreetingServiceImpl(GreetingDao greetingDao, KafkaTemplate<String, String> kafkaTemplate) {
        this.greetingDao = greetingDao;
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Lookup
//    public ProtoService getProtoService() {
//        return new ProtoService();
//    }

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, "Saving", msg);
    }

    @Override
    public Greeting save(String content) {
        sendMessage("Saving " + content);
        return greetingDao.save(new Greeting(content));
    }

}
