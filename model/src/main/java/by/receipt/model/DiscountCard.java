package by.receipt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "discount_cards")
public class DiscountCard extends DomainEntity {

    public DiscountCard(int id) {
        super(id);
    }

    public DiscountCard() {
    }
}
