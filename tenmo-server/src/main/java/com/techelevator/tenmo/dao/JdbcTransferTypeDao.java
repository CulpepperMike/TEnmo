package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcTransferTypeDao implements TransferTypeDao{


    private JdbcTemplate jdbcTemplate;

    public JdbcTransferTypeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public TransferType getTransferTypeById(int id) {
        String sql = "SELECT transfer_type_id, transfer_type_desc FROM transfer_types WHERE transfer_type_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        TransferType transferType = null;
        if(result.next()) {

            int typeId = result.getInt("transfer_type_id");
            String desc = result.getString("transfer_type_desc");

            transferType = new TransferType(typeId, desc);
        }


        return transferType;
    }

    @Override
    public TransferType getTransferTypeFromDescription(String description) {
        String sql = "SELECT transfer_type_id, transfer_type_desc FROM transfer_types WHERE transfer_type_desc = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, description);
        TransferType transferType = null;

        if(result.next()) {
            int id = result.getInt("transfer_type_id");
            String desc = result.getString("transfer_type_desc");

            transferType = new TransferType(id, desc);
        }
        return transferType;

    }
}
