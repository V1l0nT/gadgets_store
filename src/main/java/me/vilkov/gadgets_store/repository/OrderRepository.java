package me.vilkov.gadgets_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import me.vilkov.gadgets_store.domain.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
