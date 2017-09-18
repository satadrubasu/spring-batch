package com.batch.step;

import org.springframework.batch.item.ItemProcessor;

import com.batch.dto.ReadItemDTO;
import com.batch.dto.WriteItemDTO;

public class DBItemProcessor implements ItemProcessor<ReadItemDTO,WriteItemDTO> {
	
	
	@Override
	public WriteItemDTO process(final ReadItemDTO readItem) throws Exception {
		WriteItemDTO writeItem =new WriteItemDTO();
		 writeItem.setItemAlias("Aliased");
		 writeItem.setItemName(readItem.getItemName());
		 writeItem.setItemNumber(readItem.getItemNumber());
		 writeItem.setItemGroupNumber(readItem.getItemGroupNumber());
		
		return writeItem;
	}
}