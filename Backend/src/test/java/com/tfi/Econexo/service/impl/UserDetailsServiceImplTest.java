package com.tfi.Econexo.service.impl;

import com.tfi.Econexo.dto.AuthLoginRequestDTO;
import com.tfi.Econexo.model.auth.Permission;
import com.tfi.Econexo.model.auth.Role;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.repository.auth.UserRepository;
import com.tfi.Econexo.utils.JwtUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    private static UserSec userMock;

    @BeforeAll
    static void setUp() {
        Permission permission = new Permission("READ");
        Set<Permission> permissionSet = new HashSet<>();
        permissionSet.add(permission);

        Role role = new Role("DRIVER", permissionSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        userMock = new UserSec(
                "test@mail.com",
                "123456",
                true,
                true,
                true,
                true,
                roleSet
        );
    }

    @Test
    void loadUserByUsername_happyPath() {
        when(userRepository.findUserEntityByEmail(anyString())).thenReturn(Optional.ofNullable(userMock));

        UserDetails userDetails = userDetailsService.loadUserByUsername("test@mail.com");

        assertNotNull(userDetails);
        assertEquals(userMock.getEmail(), userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DRIVER")));
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("READ")));
    }

    @Test
    void loadUserByUsername_UsernameNotFoundException() {
        when(userRepository.findUserEntityByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("test@mail.com"));
    }

    @Test
    void loginUser_incorrectPassword() {
        AuthLoginRequestDTO request = new AuthLoginRequestDTO("test@mail.com", "123456");

        when(userRepository.findUserEntityByEmail(anyString())).thenReturn(Optional.ofNullable(userMock));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> userDetailsService.loginUser(request));
    }
}