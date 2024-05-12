package me.vilkov.gadgets_store.mapper;

import javax.annotation.processing.Generated;
import me.vilkov.gadgets_store.domain.dto.UserRegisterDTO;
import me.vilkov.gadgets_store.domain.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-12T22:14:17+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User registerDTOToUser(UserRegisterDTO userRegisterDTO) {
        if ( userRegisterDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userRegisterDTO.getUsername() );

        user.setPassword( passwordEncoder.encode(userRegisterDTO.getPassword()) );
        user.setRole( "ROLE_USER" );

        return user;
    }
}
