package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated")
public class AccountController {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransferDao transferDao;


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/balance/{id}", method = RequestMethod.GET)
    public Balance getBalance(User user) {
        return accountDao.getBalanceByUserId(user.getId());
    }

    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfers/{id}", method = RequestMethod.POST)
    public void createTransfer(@Valid @RequestBody Transfer transfer) {
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
    @PreAuthorize("permitAll")
    @RequestMapping(path="/account/user/{id}", method = RequestMethod.GET)
    public Account getAccountByUserId(@PathVariable int id) {
        return accountDao.getAccountByUserId(id);
    }

    @RequestMapping(path="/account/{id}", method = RequestMethod.GET)
    public Account getAccountByAccountId(@PathVariable int id) {
        return accountDao.getAccountByAccountId(id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path="/transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransfersByUserId(@PathVariable int id) {
        return transferDao.getTransferByTransferId(id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path="/transfers", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers() {
        return transferDao.getAllTransfers();
    }
}
