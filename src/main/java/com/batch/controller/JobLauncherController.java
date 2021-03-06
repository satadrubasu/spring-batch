package com.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * @author Satadru Basu
 * Entrypoint API interface for triggering the Jobs via JobLauncher
 * JobLauncher --> Job ( baseExampleJob )
 *
 */
@RestController
public class JobLauncherController {
 
    @Autowired
    Job baseExampleJob;
    
    @Autowired
    JobLauncher jobLauncher;
 
 
    /**
     * @return
     * @throws Exception
     *  http://localhost:8080/triggerBaseExampleJob
     */
    @RequestMapping("/triggerBaseExampleJob")
    public String handle() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(baseExampleJob, jobParameters);
             
        return "Batch job has been invoked";
    }
}
