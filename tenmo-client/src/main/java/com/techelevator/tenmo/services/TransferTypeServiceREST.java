package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class TransferTypeServiceREST implements TransferTypeService {

    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    public TransferTypeServiceREST(String baseUrl, AuthenticatedUser currentUser) {
        this.baseUrl = baseUrl;
        this.currentUser = currentUser;
    }

    @Override
    public com.techelevator.tenmo.model.TransferType getTransferTypeById(int id) {
        HttpEntity entity = createEntity();
        return restTemplate.exchange(baseUrl + "transfertype/" + id, HttpMethod.GET, entity, com.techelevator.tenmo.model.TransferType.class).getBody();
    }

    private HttpEntity<Void> createEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }

    @Override
    public com.techelevator.tenmo.model.TransferType getTransferTypeFromDesc(String description) {
        HttpEntity entity = createEntity();
        com.techelevator.tenmo.model.TransferType transferType = restTemplate.exchange(baseUrl + "transfertype/", HttpMethod.GET,
                entity, com.techelevator.tenmo.model.TransferType.class).getBody();
        return null;
    }
}
