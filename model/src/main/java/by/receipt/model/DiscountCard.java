package by.receipt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "discount_cards")
public class DiscountCard extends DomainEntity {

}
