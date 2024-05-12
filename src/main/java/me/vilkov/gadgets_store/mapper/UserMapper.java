package me.vilkov.gadgets_store.mapper;

import me.vilkov.gadgets_store.domain.dto.UserRegisterDTO;
import me.vilkov.gadgets_store.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {
    protected PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        return this;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRegisterDTO.getPassword()))")
    @Mapping(target = "role", constant = "ROLE_USER")
    public abstract User registerDTOToUser(UserRegisterDTO userRegisterDTO);

}