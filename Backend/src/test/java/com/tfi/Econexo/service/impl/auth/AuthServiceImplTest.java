package com.tfi.Econexo.service.impl.auth;

import com.tfi.Econexo.dto.auth.DonorRegistrationDTO;
import com.tfi.Econexo.dto.auth.DonorResponseDTO;
import com.tfi.Econexo.mappers.DonorMapper;
import com.tfi.Econexo.model.auth.Role;
import com.tfi.Econexo.model.donation.Donor;
import com.tfi.Econexo.model.location.Neighborhood;
import com.tfi.Econexo.repository.location.NeighborhoodRepository;
import com.tfi.Econexo.service.DonorService;
import com.tfi.Econexo.service.auth.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private DonorService donorService;

    @Mock
    private RoleService roleService;

    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @Mock
    private DonorMapper donorMapper;

    @InjectMocks
    private AuthServiceImpl authService;

    Role role;
    Neighborhood alberdi;
    DonorRegistrationDTO donorDTO;
    DonorResponseDTO donorResponseDTO;
    Donor donor;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setRole("DONOR");

        alberdi = new Neighborhood();
        alberdi.setId(1L);
        alberdi.setName("alberdi");

        donorDTO = new DonorRegistrationDTO(
        "test@mail.com",
        "12345678",
        "Hornito Santiagueño",
        "Hornito Alimentos SRL",
        "30712345678",
        "351155123456",
        "Av. Hipólito Yrigoyen",
        "450",
        "PB",
        "A",
        "RESTAURANT",
        -31.4233,
        -69.3214,
        1L);

        donorResponseDTO = new DonorResponseDTO(
                1L,
                "test@mail.com",
                "Hornito Santiagueño",
                "Hornito Alimentos SRL",
                "30712345678",
                "351155123456",
                "Av. Hipólito Yrigoyen",
                "450",
                "PB",
                "A",
                1L);

        donor = new Donor();
        donor.setTaxId("30712345678");
        donor.setLegalName("Hornito Alimentos SRL");
    }

    @Test
    void registerDonor_success() {
        when(donorService.findByEmail(anyString())).thenReturn(false);
        when(donorService.findByTaxId(anyString())).thenReturn(false);
        when(roleService.findByName(anyString())).thenReturn(Optional.of(role));
        when(neighborhoodRepository.findById(anyLong())).thenReturn(Optional.of(alberdi));
        when(donorMapper.toEntity(any(), any(), any())).thenReturn(donor);
        when(donorMapper.toResponseDTO(any())).thenReturn(donorResponseDTO);

        DonorResponseDTO response = authService.registerDonor(donorDTO);

        verify(donorService).save(any(Donor.class));
        assertNotNull(response);
        assertEquals(donorResponseDTO.legalName(), response.legalName());
        assertEquals(donorResponseDTO.taxId(), response.taxId());
    }

    @Test
    void registerDonor_ThrowsException_WhenEmailExists() {
        when(donorService.findByEmail(anyString())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> authService.registerDonor(donorDTO));
    }

    @Test
    void registerDonor_ThrowsException_WhenTaxIdExists(){
        when(donorService.findByTaxId(anyString())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> authService.registerDonor(donorDTO));
    }

    @Test
    void registerDonor_ThrowsException_WhenRoleNotFound(){
        when(roleService.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> authService.registerDonor(donorDTO));
    }

    @Test
    void registerDonor_ThrowsException_WhenNeighborhoodNotFound(){
        when(donorService.findByEmail(anyString())).thenReturn(false);
        when(donorService.findByTaxId(anyString())).thenReturn(false);
        when(roleService.findByName(anyString())).thenReturn(Optional.of(role));
        when(neighborhoodRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> authService.registerDonor(donorDTO));
    }
}