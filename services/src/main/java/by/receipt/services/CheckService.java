package by.receipt.services;

import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@AllArgsConstructor
public class CheckService {
    @Autowired
    private BasketService basketService;

    static final String TITLE = "CASH RECEIPT\n";
    static final String HEAD = "QTY | DESCRIPTION | PRICE | TOTAL\n";
    static final String DELIMETR = "-----------------------------------\n";


    public String getCheck(Basket basket) {
        StringBuilder stringBuilder = new StringBuilder();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        stringBuilder.append(TITLE);
        stringBuilder.append(HEAD);
        stringBuilder.append(DELIMETR);
        for (BasketItem basketItem : basket.getBasketItemList()) {
            Integer itemCount = basketItem.getCount();
            String name = basketItem.getProduct().getName();
            double price = basketItem.getProduct().getPrice();
            Double totalPrice = basketService.calculateBasketItemPrice(basketItem);
            String format = String.format("%s | %s | %s | %s\n", itemCount, name, price, totalPrice);
            stringBuilder.append(format);
        }
        stringBuilder.append(DELIMETR);
        String formatTax = String.format("TAXABLE TOT: %s\n", decimalFormat.format(basket.getPrice()));
        stringBuilder.append(formatTax);
        String formatVAT = String.format("VAT 7%%: %s\n", decimalFormat.format(basket.getPrice() * 0.07));
        stringBuilder.append(formatVAT);
        String formatTot = String.format("TOTAL: %s\n", decimalFormat.format(basket.getPrice() * 1.07));
        stringBuilder.append(formatTot);
        return String.valueOf(stringBuilder);
    }
}
