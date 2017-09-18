package com.batch.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.batch.dto.WriteItemDTO;

public class WriteItemRowMapper implements RowMapper<WriteItemDTO> {

    public static final String ID_Alias_COLUMN = "ItemAlias";
    public static final String ItemName_COLUMN = "ItemName";
    public static final String ItemNumber = "ItemNumber";
    public static final String ItemGroupId_COLUMN = "ItemGroupId";

    @Override
    public WriteItemDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        WriteItemDTO writeItem = new WriteItemDTO();

        writeItem.setItemAlias(rs.getString(ID_Alias_COLUMN));
        writeItem.setItemName(rs.getString(ItemName_COLUMN));
        writeItem.setItemNumber(rs.getInt(ItemNumber));
        writeItem.setItemGroupNumber(rs.getInt(ItemGroupId_COLUMN));

        return writeItem;
    }

}