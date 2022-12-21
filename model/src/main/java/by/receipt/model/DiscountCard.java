package by.receipt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "discount_cards")
public class DiscountCard extends DomainEntity {
    @Column(name = "percent_discount")
    private double percentDiscount;

    public DiscountCard(Integer id, double percentDiscount) {
        super(id);
        this.percentDiscount = percentDiscount;
    }
}
