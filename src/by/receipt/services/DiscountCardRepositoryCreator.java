package by.receipt.services;

import by.receipt.model.DiscountCard;
import by.receipt.repository.DiscountCardRepository;
import by.receipt.util.FileReaderUtil;

import java.util.List;

public class DiscountCardRepositoryCreator {

    public static void create(String path) {

        List<String> file = FileReaderUtil.readFromCsv(path);
        DiscountCardRepository repository = DiscountCardRepository.getInstance();

        for (String cardAndPercent : file) {
            String[] card = cardAndPercent.split(",");
            repository.save(new DiscountCard(Integer.parseInt(card[0]), Double.parseDouble(card[1])));
        }
    }
}
