package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;

import java.util.List;

public interface TransferService {
    List<Transfer>getAllTransfers(Transfer transfer);
    List<Transfer>getTransferFromUserId(int userId);
    Transfer getTransferFromId(int id);
}
