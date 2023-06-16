package by.receipt.services;

import by.receipt.api.services.IBasketService;
import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.DiscountCard;
import by.receipt.repository.BasketRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.receipt.model.enums.DiscountStatus.DISCOUNT;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BasketService implements IBasketService {
    @Value("${app.percentDiscount}")
    private double percent;
    @Autowired
    private BasketRepository basketRepository;

    @Override
    public Basket createBasket(List<BasketItem> basketItems, DiscountCard discountCard) {
        Basket basket = new Basket();
        basket.setBasketItemList(basketItems);
        basket.setDiscountCard(discountCard);
        basket.setPrice(calculateOrderPrice(basketItems, discountCard));
        for (int i = 0; i < basket.getBasketItemList().size(); i++) {
            basket.getBasketItemList().get(i).setBasket(basket);
        }
        basketRepository.save(basket);
        return basket;
    }


    private double calculateOrderPrice(List<BasketItem> basketItems, DiscountCard discountCard) {
        double price = 0;
        for (BasketItem basketItem : basketItems) {
            price += calculateBasketItemPrice(basketItem);
        }
        if (discountCard != null) {
            price *= 1.0 - discountCard.getPercentDiscount();
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
