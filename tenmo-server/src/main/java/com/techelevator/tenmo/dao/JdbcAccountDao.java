package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Balance getBalanceByUserId(int userId) {
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        Balance balance = new Balance();
        try {
            balance.setBalance(jdbcTemplate.queryForObject(sql, BigDecimal.class, userId));
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return balance;
    }

    @Override
    public Balance getBalanceByAccountId(int accountId) {
        String sql = "SELECT balance FROM account WHERE account_id = ?";
        Balance balance = new Balance();
        try {
            balance.setBalance(jdbcTemplate.queryForObject(sql, BigDecimal.class, accountId));
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return balance;
    }

    @Override
    public Account getAccountByAccountId(int accountId) {
        return null;
    }

    @Override
    public Account getAccountByUserId(int userId) {
        return null;
    }

    @Override
    public List<Account> findAllAccounts() {
        return null;
    }

    @Override
    public Account depositAccount(Account account, int id, BigDecimal amount) {
        return null;
    }

    @Override
    public Account withdrawAccount(Account account, int accountId, BigDecimal amount) {
        return null;
    }
}
