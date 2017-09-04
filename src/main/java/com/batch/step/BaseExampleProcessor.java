package com.batch.step;

import org.springframework.batch.item.ItemProcessor;

public class BaseExampleProcessor implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		return data.toUpperCase();
	}

}