package me.vilkov.gadgets_store.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "orders_gadgets",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "gadget_id")
    )
    private List<Gadget> gadgets = new ArrayList<>();

    @Column(name = "total")
    private double total;

    @Column(name = "date")
    private LocalDateTime date;

    @Override
    public String toString() {
        return String.format("%d %d %d", id, total, user.getId());
    }
}
