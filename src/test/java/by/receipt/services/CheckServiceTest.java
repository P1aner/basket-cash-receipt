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
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class CheckServiceTest {
    private CheckService checkService;
    private BasketService basketService;
    @Mock
    private BasketRepository basketRepository;

    public CheckServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.basketService = new BasketService(0.9, basketRepository);
        this.checkService = new CheckService(basketService);
    }

    @Test
    void getCheckWithoutDiscountCard() {
        List<BasketItem> list = new ArrayList<>();
        BasketItem basketItem1 = new BasketItem(new Product("Apple", 10, DiscountStatus.DISCOUNT), 10);
        BasketItem basketItem2 = new BasketItem(new Product("Apple", 10, DiscountStatus.UNDISCOUNT), 10);
        list.add(basketItem1);
        list.add(basketItem2);
        Basket basket = basketService.createBasket(list, null);
        String check = checkService.getCheck(basket);
        System.out.println(check);
        String actual = "CASH RECEIPT\n" +
                "QTY | DESCRIPTION | PRICE | TOTAL\n" +
                "-----------------------------------\n" +
                "10 | Apple | 10.0 | 90.0 discount 10%\n" +
                "10 | Apple | 10.0 | 100.0\n" +
                "-----------------------------------\n" +
                "TAXABLE TOT: 190\n" +
                "VAT 7%: 13,3\n" +
                "TOTAL: 203,3\n";
        Assertions.assertEquals(check, actual);
    }

    @Test
    void getCheckWithDiscountCard() {
        List<BasketItem> list = new ArrayList<>();
        BasketItem basketItem1 = new BasketItem(new Product("Apple", 10, DiscountStatus.DISCOUNT), 10);
        BasketItem basketItem2 = new BasketItem(new Product("Apple", 10, DiscountStatus.UNDISCOUNT), 10);
        list.add(basketItem1);
        list.add(basketItem2);
        DiscountCard discountCard = new DiscountCard(1, 0.10);
        Basket basket = basketService.createBasket(list, discountCard);
        String check = checkService.getCheck(basket);
        System.out.println(check);
        String actual = "CASH RECEIPT\n" +
                "QTY | DESCRIPTION | PRICE | TOTAL\n" +
                "-----------------------------------\n" +
                "10 | Apple | 10.0 | 90.0 discount 10%\n" +
                "10 | Apple | 10.0 | 100.0\n" +
                "-----------------------------------\n" +
                "DISCOUNT CARD #1, PERCENT:10.0%\n" +
                "TAXABLE TOT: 171\n" +
                "VAT 7%: 11,97\n" +
                "TOTAL: 182,97\n";
        Assertions.assertEquals(check, actual);
    }
}