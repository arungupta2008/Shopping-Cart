package com.saket.springboot.domain;

import com.saket.springboot.model.ShoppingCartStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "shopping_cart")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private ShoppingCartStatus status;

    @Column(name = "date")
    private Date date;

    public ShoppingCart(User user) {
        this.user = user;
        this.date = new Date();
        this.status = ShoppingCartStatus.ACTIVE;
        this.amount = 0.0;
        this.items = new HashSet<>();
    }

    public void addItem(Item itemToUpdate) {
        Item itemInDb = null;
        if (items.contains(itemToUpdate)) {
            itemInDb = items.stream().filter(x -> x.equals(itemToUpdate)).findAny().get();
        } else {
            itemInDb = itemToUpdate;
        }
        itemInDb.setQuantity(itemToUpdate.getQuantity());
        itemInDb.setUpdatedAt(new Date());
        itemToUpdate.setCart(this);
        items.add(itemInDb);
        updateAmount();
    }

    private void updateAmount() {
        this.setAmount(
                this.items.stream().collect(
                        Collectors.summarizingDouble(
                                x -> x.getQuantity() * x.getProduct().getUnitPrice()))
                        .getSum()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
