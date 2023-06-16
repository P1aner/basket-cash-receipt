package by.receipt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "baskets")
public class Basket extends DomainEntity {
    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
