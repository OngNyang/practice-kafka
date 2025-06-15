package org.example.kafkaconsumerbatch.config;

import lombok.RequiredArgsConstructor;
import org.example.kafkaconsumerbatch.job.KafkasendTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class Batchconfig {
    private final KafkasendTasklet              kafkasendTasklet;
    private final JobRepository                 jobRepository;
    private final PlatformTransactionManager    transactionManager;

    @Bean
    public Job kafkaSendJob() {
        return (new JobBuilder("kafkaSendJob", jobRepository)
                .start(kafkaSendStep())
                .build()
        );
    }

    @Bean
    public Step kafkaSendStep() {
        return (new StepBuilder("kafkaSendStep", jobRepository)
                .tasklet(kafkasendTasklet,transactionManager)
                .build()
        );
    }
}
