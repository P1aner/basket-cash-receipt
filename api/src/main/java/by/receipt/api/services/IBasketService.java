package by.receipt.api.services;

import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.DiscountCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBasketService {
    Basket createBasket(List<BasketItem> basketItems, DiscountCard discountCard);

    double calculateBasketItemPrice(BasketItem basketItem);
}
