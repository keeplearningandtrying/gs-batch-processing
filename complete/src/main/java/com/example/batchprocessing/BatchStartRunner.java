package com.example.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class BatchStartRunner implements CommandLineRunner {

    final JobLauncher jobLauncher;
    final Job job;

    public BatchStartRunner(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("param1", String.valueOf(System.currentTimeMillis()))
                .addString("param2", String.valueOf(System.currentTimeMillis()))
                .addLong("myLongId", 1000L) 
                .toJobParameters();
        
        jobLauncher.run(job, params);
    }
}
