package org.example.kafkaconsumerbe.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageListener {

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(String message) {
        log.info("✅ Kafka 메시지 수신: {}", message);
    }
}
