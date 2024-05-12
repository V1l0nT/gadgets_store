package me.vilkov.gadgets_store.service;

import jakarta.transaction.Transactional;
import me.vilkov.gadgets_store.domain.model.User;
import me.vilkov.gadgets_store.repository.UserRepository;
import me.vilkov.gadgets_store.config.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AuthService {
    private UserRepository userRepository;

    @Autowired
    public AuthService setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        return this;
    }

    public Optional<User> getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.of(((SecurityUser) authentication.getPrincipal()).getUser());
    }

    public User getAuthUserOrNull() {
        var authUser = getAuthUser().orElse(null);
        if (authUser == null) {
            return null;
        } else {
            return userRepository.findById(authUser.getId()).orElse(null);
        }
    }
}