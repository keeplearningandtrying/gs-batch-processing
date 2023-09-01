package com.example.batchprocessing;

import java.util.List;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobInstanceException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// @Component
@Order(value = 2)
public class BatchRestartRunner implements CommandLineRunner {

	final JobOperator jobOperator;

	public BatchRestartRunner(JobOperator jobOperator) {
		this.jobOperator = jobOperator;
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			List<Long> ids = jobOperator.getExecutions(39L); // job instance id
			jobOperator.restart(46L); // job execution id
		} catch (NoSuchJobInstanceException e) {
			e.printStackTrace();
		}
	}
}
