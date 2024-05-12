package me.vilkov.gadgets_store.service;

import jakarta.transaction.Transactional;
import me.vilkov.gadgets_store.domain.model.Gadget;
import me.vilkov.gadgets_store.repository.GadgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GadgetService {
    private GadgetRepository gadgetRepository;

    @Autowired
    public GadgetService setGadgetRepository(GadgetRepository gadgetRepository) {
        this.gadgetRepository = gadgetRepository;
        return this;
    }

    public List<Gadget> getAllGadgets() {
        return gadgetRepository.findAll();
    }

    public Optional<Gadget> findById(int id) {
        return gadgetRepository.findById(id);
    }

    public Gadget getById(int id) {
        return findById(id).orElseThrow();
    }

    public List<Gadget> getAll() {
        return gadgetRepository.findAll();
    }

    public void deleteById(int id) {
        gadgetRepository.deleteById(id);
    }

    public void save(Gadget gadget) {
        gadgetRepository.save(gadget);
    }
}