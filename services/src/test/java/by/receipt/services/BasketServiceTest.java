package by.receipt.services;

import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.DiscountCard;
import by.receipt.model.Product;
import by.receipt.model.enums.DiscountStatus;
import by.receipt.repository.BasketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


class BasketServiceTest {
    private BasketService basketService;
    @Mock
    private BasketRepository basketRepository;

    public BasketServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.basketService = new BasketService(0.9, basketRepository);
    }

    @Test
    void validBasketPriceTest() {
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
        Basket basket = basketService.createBasket(list, discountCard);
        Assertions.assertEquals(basket.getPrice(), 210.0);
    }

    @Test
    void calculateDiscountBasketItemPrice() {
        BasketItem basketItem1 = new BasketItem(new Product("Apple", 10, DiscountStatus.DISCOUNT), 10);
        Double price = basketService.calculateBasketItemPrice(basketItem1);
        System.out.println(price);
        Assertions.assertEquals(price, 90.0);
    }

    @Test
    void calculateUndiscountBasketItemPrice() {
        BasketItem basketItem2 = new BasketItem(new Product("Apple", 10, DiscountStatus.UNDISCOUNT), 10);
        Double price = basketService.calculateBasketItemPrice(basketItem2);
        Assertions.assertEquals(price, 100.0);
    }

    @Test
    void validBasketTest() {
        List<BasketItem> list = new ArrayList<>();
        BasketItem basketItem1 = new BasketItem(new Product("Apple", 10, DiscountStatus.DISCOUNT), 10);
        list.add(basketItem1);
        DiscountCard discountCard = new DiscountCard(1);
        Basket basket = basketService.createBasket(list, discountCard);
        Mockito.verify(basketRepository, Mockito.times(1)).save(basket);
        Assertions.assertEquals(basket.getBasketItemList(), list);
        Assertions.assertEquals(basket.getDiscountCard(), discountCard);
        Assertions.assertEquals(basket.getPrice(), 90.0);
    }
}