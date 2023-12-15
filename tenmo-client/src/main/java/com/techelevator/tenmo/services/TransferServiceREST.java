package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class TransferServiceREST implements TransferService{

    private String baseUrl;

    private Transfer transfer;

    private TransferService transferService;

    public TransferTypeService getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferTypeService transferType) {
        this.transferType = transferType;
    }

    private TransferTypeService transferType;


    private AuthenticatedUser currentUser;

    private UserService userService;

    private TransferStatusService transferStatus;

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

    private static final DecimalFormat df = new DecimalFormat("0.00");

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
    public Transfer createTransfer(int transferId, int id, int accountTo, int typeId, BigDecimal amount) {
        Transfer newTransfer = new Transfer();
        Balance balance = new Balance();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        AccountServiceREST account = new AccountServiceREST(baseUrl, currentUser);


        // sets new transfer details
        newTransfer.setId(transferId);
        newTransfer.setAccountFrom(id);
        newTransfer.setAccountTo(accountTo);
        newTransfer.setTransferTypeId(typeId);
        newTransfer.setAmount(amount);
        newTransfer.setTransferStatusId(2);

        // gets current balance
        BigDecimal currentBalance = account.getBalance();

        //updates balance
        BigDecimal updatedBalance = currentBalance.subtract(amount);
        balance.setBalance(updatedBalance);

        System.out.println("Your new balance is: $" + updatedBalance.toString() + "!");
        HttpEntity<Transfer> transferHttpEntity = new HttpEntity<>(newTransfer, headers);
        return restTemplate.exchange(baseUrl + "/transfers/" + newTransfer.getId(), HttpMethod.POST, transferHttpEntity, Transfer.class).getBody();
    }


}