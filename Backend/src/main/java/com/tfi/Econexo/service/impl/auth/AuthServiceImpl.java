package com.tfi.Econexo.service.impl.auth;

import com.tfi.Econexo.dto.DonorRegistrationDTO;
import com.tfi.Econexo.dto.DonorResponseDTO;
import com.tfi.Econexo.mappers.DonorMapper;
import com.tfi.Econexo.model.auth.Role;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.donation.Donor;
import com.tfi.Econexo.model.location.Neighborhood;
import com.tfi.Econexo.repository.location.NeighborhoodRepository;
import com.tfi.Econexo.service.DonorService;
import com.tfi.Econexo.service.auth.AuthService;
import com.tfi.Econexo.service.auth.RoleService;
import com.tfi.Econexo.service.auth.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final DonorService donorService;
    private final UserService userService;
    private final RoleService roleService;

    private final DonorMapper donorMapper;

    private final NeighborhoodRepository neighborhoodRepository;

    @Transactional
    @Override
    public DonorResponseDTO registerDonor(DonorRegistrationDTO donorDTO) {

        if(donorDTO == null){
            throw new IllegalArgumentException("Donor registration request cannot be null");
        }

        if (donorService.findByEmail(donorDTO.email()) || donorService.findByTaxId(donorDTO.taxId())) {
            throw new IllegalArgumentException("Donor already exists");
        }

        Role role = roleService.findByName("DONOR").orElseThrow(() -> new EntityNotFoundException("Role DONOR not found"));

        String password = userService.encryptPassword(donorDTO.password());

        //TODO UserMapper
        UserSec user = new UserSec();
        user.setEmail(donorDTO.email());
        user.setPassword(password);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialNonExpired(true);
        user.setRolesList(Set.of(role));

        Neighborhood neighborhood = neighborhoodRepository.findById(donorDTO.neighborhoodId())
                .orElseThrow(() -> new EntityNotFoundException("Neighborhood not found"));

        Donor donor = donorMapper.toEntity(donorDTO, user, neighborhood);

        donorService.save(donor);

        return donorMapper.toResponseDTO(donor);
    }
}
