package com.example.batchprocessing;

import java.util.List;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobInstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 2)
public class BatchRestartRunner implements CommandLineRunner {

	@Autowired
	JobOperator jobOperator;

	@Override
	public void run(String... args) throws Exception {
		Long restartId;
		try {
			List<Long> ids = jobOperator.getExecutions(1L);
			System.out.println("=====");
			System.out.println(ids);
			System.out.println("=====");
			jobOperator.restart(17L);
		} catch (NoSuchJobInstanceException e) {
			e.printStackTrace();
		}
	}
}
