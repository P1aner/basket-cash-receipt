package by.receipt.restcontroller;

import by.receipt.api.services.IBasketService;
import by.receipt.api.services.ICheckService;
import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.DiscountCard;
import by.receipt.model.Product;
import by.receipt.repository.DiscountCardRepository;
import by.receipt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BasketControllerRest {
    private static final Logger log = LoggerFactory.getLogger(BasketControllerRest.class);
    @Autowired
    private DiscountCardRepository discountCardRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IBasketService basketService;
    @Autowired
    private ICheckService checkService;

    @GetMapping("/check")
    public String getCheck(
            @RequestParam("discountCardId") Integer cardId,
            @RequestParam("itemId") List<Integer> list) {
        Basket basket = getBasket(cardId, list);
        String check = checkService.getCheck(basket);
        return check;
    }


    @GetMapping("/jsoncheck")
    public Basket getJSonCheck(
            @RequestParam("discountCardId") Integer cardId,
            @RequestParam("itemId") List<Integer> list) {
        Basket basket = getBasket(cardId, list);
        return basket;
    }

    private Basket getBasket(Integer cardId, List<Integer> list) {
        Map<Integer, Long> frequency =
                list.stream().collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()));
        List<BasketItem> basketItems = new ArrayList<>();
        DiscountCard discountCard = null;
        Optional<DiscountCard> byId = discountCardRepository.findById(cardId);
        if (byId.isPresent()) {
            discountCard = byId.get();
        } else {
            log.warn("discount card is not find");
        }
        for (Map.Entry<Integer, Long> entry : frequency.entrySet()) {
            Integer key = entry.getKey();
            Long value = entry.getValue();

            Optional<Product> byIdProduct = productRepository.findById(key);
            if (byIdProduct.isPresent()) {
                basketItems.add(new BasketItem(byIdProduct.get(), Integer.parseInt(String.valueOf(value))));
            } else {
                log.warn("product is not find");
            }
        }
        Basket basket = basketService.createBasket(basketItems, discountCard);
        return basket;
    }

}
