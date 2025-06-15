package org.example.kafkaconsumerbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumerBatchApplication implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job kafkaSendJob;

    public KafkaConsumerBatchApplication(JobLauncher jobLauncher, Job kafkaSendJob) {
        this.jobLauncher = jobLauncher;
        this.kafkaSendJob = kafkaSendJob;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerBatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // 매번 다른 파라미터로 중복 실행 방지
                .toJobParameters();
        jobLauncher.run(kafkaSendJob, jobParameters);
    }
}