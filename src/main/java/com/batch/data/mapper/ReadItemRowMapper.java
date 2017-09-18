package com.batch.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.batch.dto.ReadItemDTO;

public class ReadItemRowMapper implements RowMapper<ReadItemDTO> {

    public static final String ID_COLUMN = "ID";
    public static final String ItemName_COLUMN = "ItemName";
    public static final String ItemNumber = "ItemNumber";
    public static final String ItemGroupId_COLUMN = "ItemGroupId";

    @Override
    public ReadItemDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReadItemDTO readItem = new ReadItemDTO();

        readItem.setId(rs.getInt(ID_COLUMN));
        readItem.setItemName(rs.getString(ItemName_COLUMN));
        readItem.setItemNumber(rs.getInt(ItemNumber));
        readItem.setItemGroupNumber(rs.getInt(ItemGroupId_COLUMN));

        return readItem;
    }

}