package by.receipt;


import by.receipt.model.Receipt;

import by.receipt.services.DiscountCardRepositoryCreator;
import by.receipt.services.ProductRepositoryCreator;


public class CheckRunner {
    public static String BASKET_WAY = "C:\\Users\\pavel\\IdeaProjects\\Cash Receipt\\resourses\\Basket.csv";

    public static void main(String[] args) {

        ProductRepositoryCreator.create("C:\\Users\\pavel\\IdeaProjects\\Cash Receipt\\resourses\\Products.csv");
        DiscountCardRepositoryCreator.create("C:\\Users\\pavel\\IdeaProjects\\Cash Receipt\\resourses\\DiscountCard.csv");

        Receipt.printReceipt();
    }
}
