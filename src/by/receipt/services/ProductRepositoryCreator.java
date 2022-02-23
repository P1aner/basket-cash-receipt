package by.receipt.services;

import by.receipt.model.Product;
import by.receipt.repository.ProductRepository;
import by.receipt.util.FileReaderUtil;

import java.util.List;

public class ProductRepositoryCreator {

    public static void create(String patch) {

        List<String> fileInsert = FileReaderUtil.readFromCsv(patch);
        ProductRepository repository = ProductRepository.getInstance();

        for (String productAndCoast : fileInsert) {
            String[] product = productAndCoast.split(",");
            repository.save(new Product(Integer.parseInt(product[0]), product[1], Double.parseDouble(product[2])));
        }

    }
}
