package by.receipt.model;

public class DiscountCard extends DomainEntity {

    private Double percent;

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public DiscountCard(Integer id, Double percent) {
        super(id);
        this.percent = percent;
    }

    public Double getPercent() {
        return percent;
    }
}