package by.receipt;

import java.util.ArrayList;
import java.util.List;

public class CheckRunner {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }

        List<ArrayList<Object>> basket = Basket.getBasket("1.txt", "3.txt");
        DiscountCard card = Basket.getDiscountCard("3.txt", "2.txt");





    }
}
