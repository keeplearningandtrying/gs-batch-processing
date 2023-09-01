# Start with a success run 
* Uncomment line 11 in BatchStartRunner.java 
* Comment line 10 in BatchRestartRunner.java
* Run the application: mvn clean spring-boot:run
* Check database for job status and number of records in people table
  * people table will have the following two new records:
    * 61	JILL	DOE
    * 62	JOE	DOE
    * 63	JUSTIN	DOE
    * 64	JANE	DOE
    * 65	JOHN	DOE
  * Batch_job_execution table has the following job failure
    * 42	2	36	2023-09-01 14:01:43	2023-09-01 14:01:43	2023-09-01 14:01:43	COMPLETED	COMPLETED		2023-09-01 14:01:43

# How to test restart

## Cause a job failure
* Uncomment line 11 in BatchStartRunner.java 
* Comment line 10 in BatchRestartRunner.java
* Delete ",Doe" in file sample-date.csv line 3 
* Run the application: mvn clean spring-boot:run
* Check database for job status and number of records in people table
  * people table will have the following two new records:
    * JILL	DOE
    * JOE	DOE
  * Batch_job_execution table has the following job failure
    * 40	2	35	2023-09-01 11:14:22	2023-09-01 11:14:22	2023-09-01 11:14:22	FAILED	FAILED		2023-09-01 11:14:22
## Restart
* Comment line 11 in BatchStartRunner.java 
* Uncomment line 10 in BatchRestartRunner.java
  * Set the job instance id value in line 23 to 35
  * Set the job execution id value in line 24 to 40
* Add ",Doe-restart" in file sample-date.csv line 3 
* Run the application: mvn clean spring-boot:run
* Check database for job status and number of records in people table
  * people table will have the following two new records:
    * JUSTIN	DOE-RESTART
    * JANE	DOE
    * JOHN	DOE
  * Batch_job_execution table has the following job failure
    * 41	2	35	2023-09-01 11:19:38	2023-09-01 11:19:38	2023-09-01 11:19:38	COMPLETED	COMPLETED		2023-09-01 11:19:38
## Restart the same job again will have the following exception
  * Caused by: org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException: A job instance already exists and is complete for parameters={'myLongId':'{value=1000, type=class java.lang.Long, identifying=true}','param1':'{value=1693581262298, type=class java.lang.String, identifying=true}','param2':'{value=1693581262298, type=class java.lang.String, identifying=true}'}.  If you want to run this job again, change the parameters.
