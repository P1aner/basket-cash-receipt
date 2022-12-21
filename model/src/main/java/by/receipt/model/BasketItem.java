package by.receipt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "basket_items")
public class BasketItem extends DomainEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "count")
    private Integer count;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    public BasketItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    @Override
    public String toString() {
        return "{product=" + product +
                ", count=" + count +
                '}';
    }
}
