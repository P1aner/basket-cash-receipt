package by.receipt.api.services;

import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.DiscountCard;

import java.util.List;

public interface IBasketService {
    Basket createBasket(List<BasketItem> basketItems, DiscountCard discountCard);

    double calculateBasketItemPrice(BasketItem basketItem);
}
