package app.service.impl;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class MyKafkaListenerImpl {

  ConsumerFactory<String, String> consumerFactory;
  Consumer<String, String> consumer;

  public MyKafkaListenerImpl(ConsumerFactory<String, String> consumerFactory) {
    this.consumerFactory = consumerFactory;
    consumer = consumerFactory.createConsumer();
    consumer.subscribe(Collections.singleton("MyTopic"));
    Executor executor = Executors.newSingleThreadExecutor();
    executor.execute(this::listen);
  }

  //    @KafkaListener(topics = "MyTopic", groupId = "1")
//    public void listen(String message) {
//        System.err.println("Received Message in group 1: " + message);
//    }

//    @KafkaListener(topics = "MyTopic", groupId = "1")
//    public void listen(ConsumerRecord<String, String> record) {
//
//        System.err.println(record.value());
//    }

  private void listen() {
    while (true) {
      ConsumerRecords<String, String> poll = consumer.poll(100);
      for (ConsumerRecord record : poll) {
        System.err.println(record.value());
        System.err.println(record.offset());
        consumer.commitAsync();
      }
    }
  }
}
