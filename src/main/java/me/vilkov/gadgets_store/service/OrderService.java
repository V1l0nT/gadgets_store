package me.vilkov.gadgets_store.service;

import jakarta.transaction.Transactional;
import me.vilkov.gadgets_store.domain.model.Order;
import me.vilkov.gadgets_store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private  OrderRepository orderRepository;

    @Autowired
    public OrderService setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        return this;
    }

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    public Order getById(Integer id) {
        return findById(id).orElse(null);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
