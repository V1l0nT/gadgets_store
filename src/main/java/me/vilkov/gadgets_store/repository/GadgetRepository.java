package me.vilkov.gadgets_store.repository;

import me.vilkov.gadgets_store.domain.model.Gadget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GadgetRepository extends JpaRepository<Gadget, Integer> {
}
