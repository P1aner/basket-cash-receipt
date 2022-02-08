package by.receipt.repository;

import by.receipt.model.DiscountCard;
import java.util.ArrayList;
import java.util.List;

public class DiscountCardRepository {

    private static DiscountCardRepository instance;

    public final List<DiscountCard> discountCards = new ArrayList<>();

    public static DiscountCardRepository getInstance() {
        if (instance == null) {
            instance = new DiscountCardRepository();
        }
        return instance;
    }

    public void save(DiscountCard discountCard) {
        discountCards.add(discountCard);
    }

    public DiscountCard read(Integer id) {
        return discountCards.get(id);
    }
}