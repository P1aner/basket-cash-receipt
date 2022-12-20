package by.receipt.services;

import by.receipt.api.services.IBasketService;
import by.receipt.api.services.ICheckService;
import by.receipt.model.Basket;
import by.receipt.model.BasketItem;
import by.receipt.model.enums.DiscountStatus;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@NoArgsConstructor
public class CheckService implements ICheckService {
    @Autowired
    private IBasketService basketService;

    static final String TITLE = "CASH RECEIPT\n";
    static final String HEAD = "QTY | DESCRIPTION | PRICE | TOTAL\n";
    static final String DELIMETR = "-----------------------------------\n";

    public CheckService(IBasketService basketService) {
        this.basketService = basketService;
    }

    @Override
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
            String format = String.format("%s | %s | %s | %s", itemCount, name, price, totalPrice);
            stringBuilder.append(format);
            if (itemCount >= 5 && basketItem.getProduct().getStatus().equals(DiscountStatus.DISCOUNT)) {
                stringBuilder.append(" discount 10%\n");
            } else {
                stringBuilder.append("\n");
            }
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
