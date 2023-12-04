package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Balance;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated")
public class AccountController {
    @Autowired
    private AccountDao accountDao;

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/balance/{id}", method = RequestMethod.GET)
    public Balance getBalance(User user) {
        return accountDao.getBalanceByUserId(user.getId());
    }
}
