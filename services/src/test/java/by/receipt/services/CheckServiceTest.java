package by.receipt.services;

import by.receipt.api.services.IBasketService;
import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.DiscountCard;
import by.receipt.model.Product;
import by.receipt.model.enums.DiscountStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

class CheckServiceTest {
 //   private final IBasketService basketService = new BasketService();
    @Autowired
    private CheckService checkService/* = new CheckService(basketService)*/;

    @Test
    void getCheck() {
        List<BasketItem> list = new ArrayList<>();
        BasketItem basketItem1 = new BasketItem(new Product("Apple", 10, DiscountStatus.DISCOUNT), 10);
        BasketItem basketItem2 = new BasketItem(new Product("Apple", 10, DiscountStatus.UNDISCOUNT), 10);
        BasketItem basketItem3 = new BasketItem(new Product("Apple", 10, DiscountStatus.DISCOUNT), 1);
        BasketItem basketItem4 = new BasketItem(new Product("Apple", 10, DiscountStatus.UNDISCOUNT), 1);
        list.add(basketItem1);
        list.add(basketItem2);
        list.add(basketItem3);
        list.add(basketItem4);
        DiscountCard discountCard = new DiscountCard(1);

        Basket basket = new Basket(list, discountCard, 210.0);
        String check = "";
      System.out.println(checkService.getCheck(basket));
      //  Assertions.assertEquals(checkService.getCheck(basket), check);


    }
}