package com.batch;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;

import com.batch.controller.JobLauncherController;

/**
 * WIP -- later 
 * @author Satadru Basu
 *
 */
public class ExampleTest {
	// inject all autowired items on the JobLauncherController class 
    @InjectMocks
    private JobLauncherController jc;
    
    @Mock
    private Job simpleSPCalljob;
    
    @Before
    public void init() {
    	MockitoAnnotations.initMocks(this);
    }
	

}
