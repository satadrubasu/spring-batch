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
 
/*   @Autowired
    Job baseExampleJob;*/
    
    @Autowired
    Job dbReadExampleJob;
    
    @Autowired
    Job simpleSPCalljob;
    
    @Autowired
    JobLauncher jobLauncher;
 
 
    /**
     * @return
     * @throws Exception
     *  http://localhost:8080/triggerBaseExampleJob
     */
/*    @RequestMapping("/triggerBaseExampleJob")
    public String handle() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(baseExampleJob, jobParameters);
             
        return "Batch job has been invoked";
    }*/
    
    /**
     * @return
     * @throws Exception
     *  http://localhost:8080/triggerBaseExampleJob
     */
    @RequestMapping("/triggerDBReadWriteExampleJob")
    public String handleDBReadWrite() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(dbReadExampleJob, jobParameters);
             
        return "Batch job for DBRead Write has been invoked";
    }
    
    /**
     * @return
     * @throws Exception
     *  http://localhost:8080/spCallLoad1000
     */
    @RequestMapping("/spCallLoad1000")
    public String handleSimpleSPCall() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(simpleSPCalljob, jobParameters);
             
        return "Batch job to Trigger SP is triggered - check ReadItem table for entries with ItemName like Test1000";
    }
}
