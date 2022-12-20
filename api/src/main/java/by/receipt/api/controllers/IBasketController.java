package by.receipt.api.controllers;

import org.springframework.stereotype.Service;

@Service
public interface IBasketController {
    void printReceipt(String[] args);
}
