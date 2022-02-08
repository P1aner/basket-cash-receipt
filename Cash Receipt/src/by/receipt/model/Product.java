package by.receipt;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private Integer id;
    private String name;
    private Double price;

    public Product(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }


    public static List<Product> getBaseProducts(String patch) {
        List<Product> list = new ArrayList<>();

        List<String> fileInsert = FileReaderUtil.readFromCsv(patch);

        for (String text : fileInsert) {
            String[] qwer = text.split(",");
            list.add(new Product(Integer.parseInt(qwer[0]), qwer[1], Double.parseDouble(qwer[2])));
        }
        return list;
    }
}
