package com.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.batch.listener.BaseBatchJobListener;
import com.batch.step.BaseExampleProcessor;
import com.batch.step.BaseExampleReader;
import com.batch.step.BaseExampleWriter;

/**
 * @author Satadru Basu
 * Spring Batch COnfig
 *    --> JobBuilderFactory
 *    --> StepBuilderFactory
 *    . 
 *  Beans  
 *  ------
 *   Job ("processJob") --> Step("baseExampleJobStep")
 *   JobExecutionListener( "BaseBatchJobListener" ) 
 *   
 *   Step("baseExampleJobStep") --> Reader / Processor / Writer
 *    
 */

//@Configuration
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job baseExampleJob() {
		return jobBuilderFactory.get("baseExampleJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(baseExampleJobStep()).end().build();
	}

	/**
	 * Observe that when the chunk(4) -- resulted in a commit count of 1 with data row of 3
	 *     however when the chunk(1) -- resulted in a commit count of 4 with data row of 3
	 * @return
	 */
	@Bean
	public Step baseExampleJobStep() {
		return stepBuilderFactory.get("baseExampleJobStep").<String, String> chunk(4)
				.reader(new BaseExampleReader()).processor(new BaseExampleProcessor())
				.writer(new BaseExampleWriter()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new BaseBatchJobListener();
	}
	
	@ConfigurationProperties(prefix = "dbReadWrite.datasource")
	@Bean
	@Primary
	public DataSource dataSource() {
	    return DataSourceBuilder
	        .create()
	        .build();
	}


}
