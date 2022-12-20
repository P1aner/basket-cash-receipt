package by.receipt.controllers;

import by.receipt.model.DiscountCard;
import by.receipt.model.Product;
import by.receipt.model.enums.DiscountStatus;
import by.receipt.repository.DiscountCardRepository;
import by.receipt.repository.ProductRepository;
import by.receipt.services.BasketService;
import by.receipt.services.CheckService;
import by.receipt.utils.CheckView;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class BasketControllerTest {

    @Mock
    private BasketService basketService;
    @Mock
    private CheckService checkService;
    @Mock
    private CheckView checkView;
    @Mock
    private DiscountCardRepository discountCardRepository;
    @Mock
    private ProductRepository productRepository;
    private BasketController basketController;

    public BasketControllerTest() {
        MockitoAnnotations.initMocks(this);
        this.basketController = new BasketController(basketService, checkService, checkView, discountCardRepository, productRepository);
    }

    @Test
    void printReceiptWithoutCard() {
        Product product1 = new Product(1, "Apple", 10.0, DiscountStatus.DISCOUNT);
        Product product2 = new Product(2, "Apple", 10.0, DiscountStatus.DISCOUNT);
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product1));
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product2));
        basketController.printReceipt(new String[]{"1-1", "2-2"});

        Mockito.verify(productRepository, Mockito.times(2)).findById(Mockito.any());
        Mockito.verify(discountCardRepository, Mockito.times(0)).findById(Mockito.any());
        Mockito.verify(basketService, Mockito.times(1)).createBasket(Mockito.any(), Mockito.any());
        Mockito.verify(checkService, Mockito.times(1)).getCheck(Mockito.any());

    }

    @Test
    void printReceiptWithCard() {
        Product product1 = new Product(1, "Apple", 10.0, DiscountStatus.DISCOUNT);
        Product product2 = new Product(2, "Apple", 10.0, DiscountStatus.DISCOUNT);
        DiscountCard discountCard = new DiscountCard(1);
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product1));
        Mockito.when(productRepository.findById(2)).thenReturn(Optional.of(product2));
        Mockito.when(discountCardRepository.findById(1)).thenReturn(Optional.of(discountCard));
        basketController.printReceipt(new String[]{"1-1", "2-2", "card-1"});

        Mockito.verify(productRepository, Mockito.times(2)).findById(Mockito.any());
        Mockito.verify(discountCardRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(basketService, Mockito.times(1)).createBasket(Mockito.any(), Mockito.any());
        Mockito.verify(checkService, Mockito.times(1)).getCheck(Mockito.any());

    }

}