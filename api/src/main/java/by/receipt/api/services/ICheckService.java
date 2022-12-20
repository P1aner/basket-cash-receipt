package by.receipt.api.services;

import by.receipt.model.Basket;
import org.springframework.stereotype.Service;

@Service
public interface ICheckService {
    String getCheck(Basket basket);
}
