package com.batch.tasklet;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * This tasklet to Load test data into ReadItem Table.
 *   1. Ensure the LoadTestDataSP1000 Stored Procedure is created in the DB o that it can be called successfully
 *   2. The Tasklet should also handle gracefully if the Stored Procedure is not available.
 *   The TaskletStep repeatedly calls the execute() method until it encounters a RepeatStatus.FINISHED 
 *   or an exception.Each call to a Tasklet is wrapped in a transaction
 *   
 * @author Satadru Basu
 *
 */

@Component
public class LoadDataTasklet implements Tasklet {
	
	@Autowired
	private DataSource dataSource;
	private String sql = "{call LoadTestDataSP1000}";;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) 
			  throws Exception {
		
		logger.info(" #Stored Procedure to load 1000 rows data called at " + 
				new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
		
		JdbcTemplate jdbcTemplate =new JdbcTemplate(getDataSource());
		try {
			jdbcTemplate.execute(sql);
		}catch(DataAccessException dex)
		{
			logger.error("Unable to execute the call to Stored Procedure " + sql + "\n" + dex.getMessage());
			throw dex;
		}
		logger.info(" #Stored Procedure to load 1000 rows data returned at " + 
				new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        return RepeatStatus.FINISHED;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
