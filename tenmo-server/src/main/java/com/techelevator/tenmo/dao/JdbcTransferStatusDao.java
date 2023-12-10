package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcTransferStatusDao implements TransferStatusDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferStatusDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public TransferStatus getTransferStatusByDescription(String description) {
        String sql = "SELECT transfer_status_id, transfer_status_desc FROM transfer_status WHERE transfer_status_desc = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, description);
        TransferStatus transferStatus = null;

        if(result.next()) {
            int id = result.getInt("transfer_type_id");
            String desc = result.getString("transfer_type_desc");

            transferStatus = new TransferStatus(id, desc);
        }
        return transferStatus;
    }

    @Override
    public TransferStatus getTransferStatusById(int id) {
        String sql = "SELECT transfer_status_id, transfer_status_desc FROM transfer_statuses WHERE transfer_status_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        TransferStatus transferStatus = null;
        if(result.next()) {
            int transferStatusId = result.getInt("transfer_status_id");
            String desc = result.getString(("transfer_status_desc"));
            transferStatus = new TransferStatus(transferStatusId, desc);

        }

        return transferStatus;
    }

}
