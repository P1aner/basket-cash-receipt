package by.receipt.controllers;

import by.receipt.api.controllers.IBasketController;
import by.receipt.api.services.IBasketService;
import by.receipt.api.services.ICheckService;
import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.DiscountCard;
import by.receipt.model.Product;
import by.receipt.repository.DiscountCardRepository;
import by.receipt.repository.ProductRepository;
import by.receipt.utils.CheckView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BasketController implements IBasketController {
    private static final Logger log = LoggerFactory.getLogger(IBasketController.class);
    @Autowired
    private IBasketService basketService;
    @Autowired
    private ICheckService checkService;
    @Autowired
    private CheckView checkView;
    @Autowired
    private DiscountCardRepository discountCardRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void printReceipt(String[] args) {
        List<BasketItem> basketItems = new ArrayList<>();
        DiscountCard discountCard = null;
        for (int i = 0; i < args.length - 1; i++) {
            splitStringItem(basketItems, args[i]);
        }

        String[] strings = args[args.length - 1].split("-");
        if (strings[0].equals("card")) {
            int i = Integer.parseInt(strings[1]);
            Optional<DiscountCard> byId = discountCardRepository.findById(i);
            if (byId.isPresent()) {
                discountCard = byId.get();
            } else {
                log.warn("discount card is not find");
            }
        } else {
            splitStringItem(basketItems, args[args.length - 1]);
        }
        Basket basket = basketService.createBasket(basketItems, discountCard);
        String check = checkService.getCheck(basket);

        checkView.filePrint(check);
        checkView.consolePrint(check);
    }

    private void splitStringItem(List<BasketItem> basketItems, String split) {
        String[] item = split.split("-");
        int id = Integer.parseInt(item[0]);
        int count = Integer.parseInt(item[1]);
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            basketItems.add(new BasketItem(byId.get(), count));
        } else {
            log.warn("product is not find");
        }
    }
}
