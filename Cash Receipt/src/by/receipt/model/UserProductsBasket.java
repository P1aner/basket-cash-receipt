package by.receipt.model;

import by.receipt.CheckRunner;
import by.receipt.repository.ProductRepository;
import by.receipt.util.FileReaderUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserProductsBasket {
    public static List<List<Object>> basket = new ArrayList<>();

    static {

        List<Product> products = ProductRepository.getInstance().products;
        List<String> list = FileReaderUtil.readFromCsv(CheckRunner.BASKET_WAY);

        for (int i = 0; i < (list.size() - 1); i++) {
            String[] arg = list.get(i).split(",");
            for (Product product : products) {
                if (Integer.parseInt(arg[0]) == product.getId()) {
                    basket.add(new ArrayList<>(Arrays.asList(product, Integer.parseInt(arg[1]))));
                }
            }
        }
    }
}

