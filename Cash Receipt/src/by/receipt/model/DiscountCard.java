package by.receipt;

import java.util.ArrayList;
import java.util.List;

public class DiscountCard {
    private Integer id;
    private Double percent;

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public DiscountCard(Integer id, Double percent) {
        this.id = id;
        this.percent = percent;
    }


    public Integer getId() {
        return id;
    }

    public Double getPercent() {
        return percent;
    }

    public static List<DiscountCard> getBaseDiscountCard(String path) {
        List<DiscountCard> list = new ArrayList<>();

        List<String> file = FileReaderUtil.readFromCsv(path);

        for (String cardAndPercent : file) {
            String[] card = cardAndPercent.split(",");
            list.add(new DiscountCard(Integer.parseInt(card[0]), Double.parseDouble(card[1])));
        }
        return list;
    }
}
