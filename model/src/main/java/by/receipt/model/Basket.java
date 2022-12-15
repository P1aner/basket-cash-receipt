package by.receipt.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "baskets")
public class Basket extends DomainEntity {
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BasketItem> basketItemList;
    @ManyToOne
    @JoinColumn(name = "discount_card_id")
    private DiscountCard discountCard;
    @Column(name = "price")
    private Double price;

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + this.getId() +
                "basketItemList=" + basketItemList +
                ", discountCard=" + discountCard +
                ", price=" + price +
                '}';
    }
}
