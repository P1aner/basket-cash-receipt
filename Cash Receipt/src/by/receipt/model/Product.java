package by.receipt.model;

public class Product extends DomainEntity {

    private String name;
    private Double price;

    public Product(Integer id, String name, Double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}