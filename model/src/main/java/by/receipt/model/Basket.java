package by.receipt.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "baskets")
public class Basket extends DomainEntity {
    @OneToMany(mappedBy = "basket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BasketItem> basketItemList;
    @ManyToOne
    @JoinColumn(name = "discount_card_id")
    private DiscountCard discountCard;
    @Column(name = "price")
    private Double price;

    @Override
    public String toString() {
        return "\nBasket: " +
                "{id=" + this.getId() +
                ", discountCard=" + discountCard +
                ", price=" + price +
                "\nbasketItemList=[" + basketItemList +
                "]}";
    }
}
