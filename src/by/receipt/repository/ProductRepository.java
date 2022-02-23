package by.receipt.repository;

import by.receipt.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static ProductRepository instance;

    public final List<Product> products = new ArrayList<>();

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public void save(Product product) {
        products.add(product);
    }

    public Product read(Integer id) {
        return products.get(id);
    }
}