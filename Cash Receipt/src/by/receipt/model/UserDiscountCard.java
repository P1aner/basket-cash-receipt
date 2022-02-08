package by.receipt.model;

import by.receipt.CheckRunner;
import by.receipt.repository.DiscountCardRepository;
import by.receipt.util.FileReaderUtil;

import java.util.List;

public class UserDiscountCard {
    public static DiscountCard card = null;

    static {
        List<String> list = FileReaderUtil.readFromCsv(CheckRunner.BASKET_WAY);
        String[] arg = list.get(list.size() - 1).split(",");

        for (DiscountCard discountCard : DiscountCardRepository.getInstance().discountCards) {
            if (Integer.parseInt(arg[0]) == discountCard.getId()) {
                card = discountCard;
            }
        }
    }

}
