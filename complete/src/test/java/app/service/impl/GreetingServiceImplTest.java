package app.service.impl;

import app.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.ClientResponse;
import org.apache.kafka.clients.Metadata;
import org.apache.kafka.clients.StaleMetadataException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.NoOffsetForPartitionException;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient;
import org.apache.kafka.clients.consumer.internals.RequestFuture;
import org.apache.kafka.clients.consumer.internals.RequestFutureAdapter;
import org.apache.kafka.clients.consumer.internals.SubscriptionState;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.InvalidMetadataException;
import org.apache.kafka.common.protocol.ApiKeys;
import org.apache.kafka.common.protocol.Errors;
import org.apache.kafka.common.protocol.types.Struct;
import org.apache.kafka.common.requests.ListOffsetRequest;
import org.apache.kafka.common.requests.ListOffsetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Slf4j
@SpringBootTest
class GreetingServiceImplTest {

    @Autowired
    GreetingService greetingService;

    @Autowired
    KafkaAdminClient kafkaAdminClient;

    @Autowired
    AdminClient adminClient;

    @Autowired
    KafkaConsumer consumer;


    @Test
    void save() throws ExecutionException, InterruptedException {
        greetingService.save("Test content");

        ListTopicsResult listTopicsResult = adminClient.listTopics();
        KafkaFuture<Set<String>> names = listTopicsResult.names();
        Set<String> strings = names.get();

        System.err.println("Before deleting: " + strings);


        adminClient.deleteTopics(strings);

        List<TopicPartition> topicsForReset = new ArrayList<>();

        for (String topicName : strings) {
            DescribeTopicsResult result = adminClient.describeTopics(Collections.singleton(topicName));
            Map<String, KafkaFuture<TopicDescription>> values = result.values();
            KafkaFuture<TopicDescription> topicDescription = values.get(topicName);
            int partitions = topicDescription.get().partitions().size();
            for (int i = 0; i < partitions; i++) {
                topicsForReset.add(new TopicPartition(topicName, i));
            }
        }
        consumer.seekToEnd(topicsForReset);

//        Producer producer = new KafkaProducer(configProperties);
//        producer.partitionsFor("test")

        ListTopicsResult listTopicsResult2 = adminClient.listTopics();
        KafkaFuture<Set<String>> names2 = listTopicsResult2.names();
        Set<String> strings2 = names2.get();

        System.err.println("After deleting: " + strings2);


//        ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
//        KafkaFuture<Set<String>> names = listTopicsResult.names();
    }

//    private void resetOffset(TopicPartition partition) {
//        OffsetResetStrategy strategy = subscriptions.resetStrategy(partition);
//        final long timestamp;
//        if (strategy == OffsetResetStrategy.EARLIEST)
//            timestamp = ListOffsetRequest.EARLIEST_TIMESTAMP;
//        else if (strategy == OffsetResetStrategy.LATEST)
//            timestamp = ListOffsetRequest.LATEST_TIMESTAMP;
//        else
//            throw new NoOffsetForPartitionException(partition);
//
//        long offset = listOffset(partition, timestamp);
//
//        // we might lose the assignment while fetching the offset, so check it is still active
//        if (subscriptions.isAssigned(partition))
//            this.subscriptions.seek(partition, offset);
//    }
//
//    private long listOffset(TopicPartition partition, long timestamp) {
//        while (true) {
//            RequestFuture<Long> future = sendListOffsetRequest(partition, timestamp);
//            client.poll(future);
//
//            if (future.succeeded())
//                return future.value();
//
//            if (!future.isRetriable())
//                throw future.exception();
//
//            if (future.exception() instanceof InvalidMetadataException)
//                client.awaitMetadataUpdate();
////            else {
////                time.sleep(retryBackoffMs);
////            }
//        }
//    }
//
//    private RequestFuture<Long> sendListOffsetRequest(TopicPartition topicPartition, long timestamp) {
//        Map<TopicPartition, ListOffsetRequest.PartitionData> partitions = new HashMap<>(1);
//        partitions.put(topicPartition, new ListOffsetRequest.PartitionData(timestamp, 1));
//        PartitionInfo info = metadata.fetch().partition(topicPartition);
//        if (info == null) {
//            metadata.add(topicPartition.topic());
//            log.debug("Partition {} is unknown for fetching offset, wait for metadata refresh", topicPartition);
//            return RequestFuture.coordinatorNotAvailable();
//        } else if (info.leader() == null) {
//            log.debug("Leader for partition {} unavailable for fetching offset, wait for metadata refresh", topicPartition);
//            return RequestFuture.coordinatorNotAvailable();
////            return RequestFuture.leaderNotAvailable();
//        } else {
//            Node node = info.leader();
//            ListOffsetRequest request = new ListOffsetRequest(-1, partitions);
//            return client.send(node, ApiKeys.LIST_OFFSETS, request)
//                    .compose(new RequestFutureAdapter<ClientResponse, Long>() {
//                        @Override
//                        public void onSuccess(ClientResponse response, RequestFuture<Long> future) {
//                            handleListOffsetResponse(topicPartition, response, future);
//                        }
//                    });
//        }
//    }
//
//    private void handleListOffsetResponse(TopicPartition topicPartition,
//                                          ClientResponse clientResponse,
//                                          RequestFuture<Long> future) {
//        ListOffsetResponse lor = new ListOffsetResponse(new Struct()clientResponse.responseBody());
//        short errorCode = lor.responseData().get(topicPartition).error.code();
//        if (errorCode == Errors.NONE.code()) {
//            List<Long> offsets = lor.responseData().get(topicPartition).offsets;
//            if (offsets.size() != 1)
//                throw new IllegalStateException("This should not happen.");
//            long offset = offsets.get(0);
//            log.debug("Fetched offset {} for partition {}", offset, topicPartition);
//
//            future.complete(offset);
//        } else if (errorCode == Errors.NOT_LEADER_FOR_PARTITION.code()
//                || errorCode == Errors.UNKNOWN_TOPIC_OR_PARTITION.code()) {
//            log.debug("Attempt to fetch offsets for partition {} failed due to obsolete leadership information, retrying.",
//                    topicPartition);
//            future.raise(Errors.forCode(errorCode));
//        } else {
//            log.warn("Attempt to fetch offsets for partition {} failed due to: {}",
//                    topicPartition, Errors.forCode(errorCode).message());
//            future.raise(new StaleMetadataException());
//        }
//    }


}