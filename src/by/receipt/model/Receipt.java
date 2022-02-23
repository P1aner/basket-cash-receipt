package by.receipt.model;

import java.text.DecimalFormat;
import java.util.List;

public class Receipt {
    static final String TITLE = "CASH RECEIPT";
    static final String HEAD = "QTY | DESCRIPTION | PRICE | TOTAL";
    static final String DELIMETR = "-----------------------------------";

    public static void printReceipt() {
        Double price = 0.0;
        Double total = 0.0;
        String promotional;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double vatPercent = UserDiscountCard.card.getPercent();
        List<List<Object>> basket = UserProductsBasket.basket;
        System.out.println(TITLE);
        System.out.println(HEAD);
        System.out.println(DELIMETR);
        for (List<Object> list : basket) {
            Product product = (Product) list.get(0);
            Double coast = (product.getPrice()) * ((Integer) list.get(1));
            price += coast;

            if (((Integer) list.get(1)) < 5) {
                total += coast * vatPercent;
                promotional = "";
            } else {
                total += coast * vatPercent * 0.9;
                promotional = " - дополнительная скидка 10%";
            }
            System.out.println(list.get(1) + " | " + product.getName() + " | " + decimalFormat.format(product.getPrice()) + " | " + decimalFormat.format(coast) + promotional);
        }
        Double vat = price - total;
        System.out.println(DELIMETR);
        System.out.println("ALL TOTOTAL: " + decimalFormat.format(price));
        System.out.println("DISCOUNT " + ((1 - vatPercent) * 100) + "%: " + decimalFormat.format(vat));
        System.out.println("TOTAL: " + decimalFormat.format(total));

    }


}