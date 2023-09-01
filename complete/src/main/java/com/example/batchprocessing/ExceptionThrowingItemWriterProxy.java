package com.example.batchprocessing;

import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.lang.Nullable;

/**
 * Hacked {@link ItemReader} that throws exception on a given record number (useful for
 * testing restart).
 *
 * @author Robert Kasanicky
 * @author Lucas Ward
 *
 */
public class ExceptionThrowingItemWriterProxy<T> implements ItemWriter<T> {

	private int counter = 0;

	private int throwExceptionOnRecordNumber = 4;

	private JdbcBatchItemWriter<T> delegate;

	/**
	 * @param throwExceptionOnRecordNumber The number of record on which exception should
	 * be thrown
	 */
	public void setThrowExceptionOnRecordNumber(int throwExceptionOnRecordNumber) {
		this.throwExceptionOnRecordNumber = throwExceptionOnRecordNumber;
	}

	public void setDelegate(JdbcBatchItemWriter<T> delegate) {
		this.delegate = delegate;
	}

	@Nullable
	@Override
	public void write(Chunk<? extends T> arg0) throws Exception {
		counter++;
		if (counter == throwExceptionOnRecordNumber) {
			throw new UnexpectedJobExecutionException("Planned failure on count=" + counter);
		}
		delegate.write(arg0);
	}

}
