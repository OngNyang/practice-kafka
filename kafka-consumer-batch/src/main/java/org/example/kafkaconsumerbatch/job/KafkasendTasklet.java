package org.example.kafkaconsumerbatch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.kafkaconsumerbatch.kafka.service.KafkaProducerService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkasendTasklet implements Tasklet {
    private final KafkaProducerService kafkaProducerService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String  message = "Sending message - " + System.currentTimeMillis();

        kafkaProducerService.send("test-topic", message);

        return (RepeatStatus.FINISHED);
    }
}
