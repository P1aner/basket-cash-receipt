package by.receipt.services;

import by.receipt.api.services.IBasketService;
import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.DiscountCard;
import by.receipt.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.receipt.model.enums.DiscountStatus.DISCOUNT;

@Service
public class BasketService implements IBasketService {
    @Value("${percentDiscount}")
    private double percent;
    @Autowired
    private BasketRepository basketRepository;

    @Override
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
            calculateBasketItemPrice(basketItem);
        }
        return price;
    }

    @Override
    public double calculateBasketItemPrice(BasketItem basketItem) {
        double itemPrice;
        Integer itemCount = basketItem.getCount();
        double price = basketItem.getProduct().getPrice();
        if (basketItem.getProduct().getStatus().equals(DISCOUNT) && itemCount >= 5) {
            itemPrice = price * itemCount * percent;
        } else {
            itemPrice = price * itemCount;
        }
        return itemPrice;
    }

}
