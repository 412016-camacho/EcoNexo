package com.tfi.Econexo.mappers;

import com.tfi.Econexo.model.auth.Role;
import com.tfi.Econexo.model.auth.UserSec;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-26T15:35:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserSec toEntity(String email, String encryptedPassword, Role role) {
        if ( email == null && encryptedPassword == null && role == null ) {
            return null;
        }

        UserSec userSec = new UserSec();

        userSec.setEmail( email );
        userSec.setPassword( encryptedPassword );
        userSec.setEnabled( true );
        userSec.setAccountNonExpired( true );
        userSec.setAccountNonLocked( true );
        userSec.setCredentialNonExpired( true );
        userSec.setRolesList( java.util.Set.of(role) );

        return userSec;
    }
}
