package me.vilkov.gadgets_store.service;

import me.vilkov.gadgets_store.domain.model.Gadget;
import me.vilkov.gadgets_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.vilkov.gadgets_store.domain.dto.UserRegisterDTO;
import me.vilkov.gadgets_store.domain.model.Order;
import me.vilkov.gadgets_store.domain.model.User;
import me.vilkov.gadgets_store.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@Service
@jakarta.transaction.Transactional
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private AuthService authService;
    private GadgetService gadgetService;
    private OrderService orderService;

    @Autowired
    public UserService setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        return this;
    }

    @Autowired
    public UserService setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
        return this;
    }

    @Autowired
    public UserService setAuthService(AuthService authService) {
        this.authService = authService;
        return this;
    }

    @Autowired
    public UserService setGadgetService(GadgetService gadgetService) {
        this.gadgetService = gadgetService;
        return this;
    }

    @Autowired
    public UserService setOrderService(OrderService orderService) {
        this.orderService = orderService;
        return this;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        save(userMapper.registerDTOToUser(userRegisterDTO));
    }

    public boolean addToCartByGadgetId(int id) {
        var user = userRepository.findById(authService.getAuthUser().orElseThrow().getId()).orElseThrow();

        var gadget = user.getGadgets().stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        if (gadget.isEmpty()) {
            var gadgetToAdd = gadgetService.getById(id);
            user.getGadgets().add(gadgetToAdd);
            save(user);
        }
        return true;
    }

    public List<Gadget> getCart() {
        return userRepository.findById(authService.getAuthUser().orElseThrow().getId()).orElseThrow().getGadgets();
    }

    public void removeFromCartByGadgetId(int id) {
        var user = userRepository.findById(authService.getAuthUser().orElseThrow().getId()).orElseThrow();
        user.getGadgets().removeIf(p -> p.getId() == id);
        save(user);
    }

    public double getCartTotalPrice() {
        return getCart().stream()
                .mapToDouble(Gadget::getPrice)
                .sum();
    }

    public Order order() {
        var user = userRepository.findById(authService.getAuthUser().orElseThrow().getId()).orElseThrow();
        var order = new Order();
        double totalPrice = 0;
        for (Gadget gadget : user.getGadgets()) {
            var pr = gadgetService.getById(gadget.getId());
            totalPrice += pr.getPrice();
            order.getGadgets().add(pr);
        }
        order.setUser(user);
        order.setTotal(totalPrice);
        order.setDate(java.time.LocalDateTime.now());

        user.getGadgets().clear();

        save(user);
        orderService.save(order);
        return order;
    }

    public Order getOrderById(int id) {
        var user = authService.getAuthUser().orElseThrow();
        var order = orderService.getById(id);

        if (order == null) {
            return null;
        }

        if (order.getUser().getId() == user.getId()) {
            return order;
        } else {
            return null;
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}