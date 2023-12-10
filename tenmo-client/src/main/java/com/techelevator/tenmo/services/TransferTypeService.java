package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.TransferType;

public interface TransferTypeService {
    TransferType getTransferTypeById(int id);
    TransferType getTransferTypeFromDesc(String description);
}
