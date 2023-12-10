package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@PreAuthorize("isAuthenticated")
public class AccountController {
    @Autowired
    private AccountDao accountDao;
    private TransferDao transferDao;


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/balance/{id}", method = RequestMethod.GET)
    public Balance getBalance(User user) {
        return accountDao.getBalanceByUserId(user.getId());
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/transfers/{id}", method = RequestMethod.GET)
    public void newTransfer(Transfer transfer) {
        // gets accounts for Transfer from ids
        Account account = accountDao.getAccountByAccountId(transfer.getAccountFrom());
        Account accountTo = accountDao.getAccountByAccountId(transfer.getAccountTo());
        // amount of money being transferred
        BigDecimal amount = transfer.getAmount();
        // does transfer
        account.getBalance().sendMoney(amount);
        accountTo.getBalance().receiveMoney(amount);
        transferDao.createTransfer(transfer);
        // updates account Balances
        accountDao.updateBalance(account.getId(), account.getBalance());
        accountDao.updateBalance(accountTo.getId(), accountTo.getBalance());
    }
}
