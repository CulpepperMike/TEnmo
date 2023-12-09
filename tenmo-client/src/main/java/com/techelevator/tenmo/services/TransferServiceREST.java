package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class TransferServiceREST implements TransferService{

    private String baseUrl;

    private Transfer transfer;

    private TransferService transferService;


    private AuthenticatedUser currentUser;

    private UserService userService;

    private AccountService accountService;

    private TransferStatusService transferStatus;


    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public AuthenticatedUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AuthenticatedUser currentUser) {
        this.currentUser = currentUser;
    }

    public TransferServiceREST(String base_Url, AuthenticatedUser currentUser){
        this.baseUrl = base_Url;
        this.currentUser = currentUser;
    }

    public TransferServiceREST(TransferService transferService){
        this.transferService = transferService;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Transfer> getAllTransfers(Transfer transfer) {

        return null;
    }

    @Override
    public List<Transfer>getTransfersByUserId(int userId) {
        return null;
    }

    @Override
    public Transfer getTransferFromId(int id) {
        return null;
    }

    @Override
    public Transfer sendTransfer(int id, BigDecimal amount) {

        Transfer newTransfer = new Transfer();

        newTransfer.setAccountFrom(currentUser.getUser().getId());
        newTransfer.setAccountTo(id);
        newTransfer.setAmount(amount);
        newTransfer.setDescription("Approved");

        BigDecimal myBalance = accountService.getAccountByUserId(currentUser.getUser().getId()).getBalance().getBalance();

        BigDecimal updatedBalance = myBalance.subtract(amount);
        accountService.getAccountById(currentUser.getUser().getId()).getBalance().setBalance(updatedBalance);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Transfer> transferHttpEntity = new HttpEntity<>(newTransfer, headers);


            restTemplate.postForEntity(baseUrl + "transfers", transferHttpEntity, Transfer.class);

        System.out.println("Your new balance is: " + accountService.getAccountById(currentUser.getUser().getId()).getBalance().getBalance() + "!");
        return newTransfer;
    }


}