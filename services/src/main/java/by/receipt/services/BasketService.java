package by.receipt.services;

import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.DiscountCard;
import by.receipt.model.enums.DiscountStatus;
import by.receipt.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.receipt.model.enums.DiscountStatus.DISCOUNT;

@Service
public class BasketService {
    //  @Value(value = "0.90")
    private double percent = 0.9;
    @Autowired
    BasketRepository basketRepository;

    public Basket createBasket(List<BasketItem> basketItems, DiscountCard discountCard) {
        Basket basket = new Basket();
        basket.setBasketItemList(basketItems);
        basket.setDiscountCard(discountCard);
        basket.setPrice(calculateOrderPrice(basketItems));
        basketRepository.save(basket);
        return basket;
    }


    private double calculateOrderPrice(List<BasketItem> basketItems) {
        double price = 0;
        for (BasketItem basketItem : basketItems) {
            if (basketItem.getProduct().getStatus().equals(DiscountStatus.DISCOUNT)) {
                if (basketItem.getCount() >= 5) {
                    price += basketItem.getCount() * basketItem.getProduct().getPrice() * percent;
                } else {
                    price += basketItem.getCount() * basketItem.getProduct().getPrice();
                }
            } else {
                price += basketItem.getCount() * basketItem.getProduct().getPrice();
            }
        }
        return price;
    }

    public double calculateBasketItemPrice(BasketItem basketItem) {
        double itemPrice;
        Integer itemCount = basketItem.getCount();
        double price = basketItem.getProduct().getPrice();
        if (basketItem.getProduct().getStatus().equals(DISCOUNT) && itemCount >= 5) {
            itemPrice = price * itemCount * 0.9;
        } else {
            itemPrice = price * itemCount;
        }
        return itemPrice;
    }

}
