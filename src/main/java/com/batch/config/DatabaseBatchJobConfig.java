package com.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.batch.dto.ReadItemDTO;
import com.batch.dto.WriteItemDTO;
import com.batch.listener.BaseBatchJobListener;
import com.batch.step.DBItemProcessor;
import com.batch.tasklet.LoadDataTasklet;
 
@Configuration
public class DatabaseBatchJobConfig  {
	
	
    @Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
    private static final String QUERY_ITEM = "SELECT ID,ItemName,ItemNumber FROM ReadItem ";
           // "ORDER BY email_address ASC";
 
    
    //--------------------------------- Definition of different Jobs ------------------
    
	@Bean
	public Job dbReadExampleJob() {
		return jobBuilderFactory.get("dbReadExampleJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(dbReadWriteExampleJobStep()).end().build();
	}
	
	/**
	 * simpleSPCalljob -- to test two use cases when the SP is not present vs when the SP is present
	 * @return
	 */
	@Bean
    public Job simpleSPCalljob() {
        return jobBuilderFactory.get("simpleSPCalljob")
                .incrementer(new RunIdIncrementer())
                .start(dbCallSPTaskletExample()).build();
    }

	// ---------------------- End of Job Definitions -----------------
	// Definition of different Steps
	/**
	 * In this class we are configuring two readers :
	 * @return
	 */
	@Bean
	public Step dbReadWriteExampleJobStep() {
		return stepBuilderFactory.get("dbReadWriteExampleJobStep").<ReadItemDTO, WriteItemDTO> chunk(1000)
				.reader(databaseItemReader(dataSource()))
				.processor(databaseItemProcessor())
				.writer(databaseItemwriter(dataSource())).build();
	}
	
    @Bean
	public Step dbCallSPTaskletExample() {
	        return stepBuilderFactory.get("dbCallSPTaskletExample")
	        		.tasklet(getloadDataTasklet())
	                .build();
	    }
	
	
    @Bean
    ItemReader<ReadItemDTO> databaseItemReader(DataSource dataSource) {
    	
        JdbcCursorItemReader<ReadItemDTO> databaseReader = new JdbcCursorItemReader<>();
 
        databaseReader.setDataSource(dataSource);
        databaseReader.setSql(QUERY_ITEM);
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(ReadItemDTO.class));
 
        return databaseReader;
    }
    
    @Bean
    public JdbcBatchItemWriter<WriteItemDTO> databaseItemwriter(DataSource dataSource) {
        JdbcBatchItemWriter<WriteItemDTO> writer = new JdbcBatchItemWriter<WriteItemDTO>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<WriteItemDTO>());
        writer.setSql("INSERT INTO WriteItem (ItemName,ItemAlias,ItemNumber,ItemGroupId) VALUES "
        		+ "(:itemName,:itemAlias,:itemNumber,:itemGroupNumber)");
        writer.setDataSource(dataSource);
        return writer;
    }
    
    /**
     * ItemProcessor Bean
     * @return ItemProcessor
     */
    @Bean
    public ItemProcessor<ReadItemDTO, WriteItemDTO> databaseItemProcessor() {
        return  (ItemProcessor<ReadItemDTO, WriteItemDTO>) new DBItemProcessor();
    }
    
    
	@Bean
	public JobExecutionListener listener() {
		return new BaseBatchJobListener();
	}
	/*  @Bean
	    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	        return new JdbcTemplate(dataSource);
	    }
	    @Bean
		public DataSource getDataSource() {
		    BasicDataSource dataSource = new BasicDataSource();
		    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		    dataSource.setUrl("jdbc:mysql://localhost:3306/concretepage");
		    dataSource.setUsername("root");
		    dataSource.setPassword("");
		    return dataSource;
		}
	} */
	@ConfigurationProperties(prefix = "dbReadWrite.datasource")
	@Bean
	@Primary
	public DataSource dataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	
	@Bean
	public Tasklet getloadDataTasklet()
	{
		return new LoadDataTasklet();
	}
}