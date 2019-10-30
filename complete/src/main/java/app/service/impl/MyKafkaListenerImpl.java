package app.service.impl;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class MyKafkaListenerImpl {

  ConsumerFactory<String, String> consumerFactory;
  Consumer<String, String> consumer;
  @Autowired
  AdminClient adminClient;

  public MyKafkaListenerImpl(ConsumerFactory<String, String> consumerFactory) {
    this.consumerFactory = consumerFactory;
    consumer = consumerFactory.createConsumer();
    consumer.subscribe(Collections.singleton("MyTopic"));
    Executor executor = Executors.newSingleThreadExecutor();
//    executor.execute(this::listen);
  }

      @KafkaListener(topics = "MyTopic", groupId = "1")
    public void listen(String message) {
        System.err.println("Received Message in group 1: " + message);
    }


//  private void listen() {
//    while (true) {
//      ConsumerRecords<String, String> poll = consumer.poll(100);
//      for (ConsumerRecord record : poll) {
//        System.err.println(record.value());
//        System.err.println(record.offset());
//        adminClient.listTopics();
//        consumer.commitAsync();
//      }
//    }
//  }
}
