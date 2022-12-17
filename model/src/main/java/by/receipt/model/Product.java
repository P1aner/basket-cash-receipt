package by.receipt.model;

import by.receipt.model.enums.DiscountStatus;
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
@Table(name = "products")
public class Product extends DomainEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DiscountStatus status;

    @Override
    public String toString() {
        return "Product:{" +
                "id=" + this.getId() +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
