package by.receipt;


import by.receipt.model.Receipt;

import by.receipt.services.DiscountCardRepositoryCreator;
import by.receipt.services.ProductRepositoryCreator;


public class CheckRunner {
    public static String BASKET_WAY = "./resources/Basket.csv";

    public static void main(String[] args) {

        ProductRepositoryCreator.create("./resources/Products.csv");
        DiscountCardRepositoryCreator.create("./resources/DiscountCard.csv");

        Receipt.printReceipt();
    }
}
