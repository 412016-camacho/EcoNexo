package com.tfi.Econexo.service.impl.auth;

import com.tfi.Econexo.dto.auth.DonorRegistrationDTO;
import com.tfi.Econexo.dto.auth.DonorResponseDTO;
import com.tfi.Econexo.dto.auth.NgoRegistrationDTO;
import com.tfi.Econexo.dto.auth.NgoResponseDTO;
import com.tfi.Econexo.exception.ConflictException;
import com.tfi.Econexo.mappers.DonorMapper;
import com.tfi.Econexo.mappers.NgoMapper;
import com.tfi.Econexo.mappers.UserMapper;
import com.tfi.Econexo.model.auth.Role;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.donation.Donor;
import com.tfi.Econexo.model.location.Neighborhood;
import com.tfi.Econexo.model.ngo.Ngo;
import com.tfi.Econexo.service.DonorService;
import com.tfi.Econexo.service.NeighborhoodService;
import com.tfi.Econexo.service.NgoService;
import com.tfi.Econexo.service.auth.AuthService;
import com.tfi.Econexo.service.auth.RoleService;
import com.tfi.Econexo.service.auth.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final DonorService donorService;
    private final UserService userService;
    private final RoleService roleService;
    private final NgoService ngoService;

    private final DonorMapper donorMapper;
    private final UserMapper userMapper;
    private final NgoMapper ngoMapper;

    private final NeighborhoodService neighborhoodService;

    @Transactional
    @Override
    public DonorResponseDTO registerDonor(DonorRegistrationDTO donorDTO) {

        if(donorDTO == null){
            throw new IllegalArgumentException("Donor registration request cannot be null");
        }

        if (donorService.findByEmail(donorDTO.email()) || donorService.findByTaxId(donorDTO.taxId())) {
            throw new ConflictException("Donor already exists");
        }

        Role role = roleService.findByName("DONOR").orElseThrow(() -> new EntityNotFoundException("Role DONOR not found"));

        String password = userService.encryptPassword(donorDTO.password());

        UserSec user = userMapper.toEntity(donorDTO.email(), password, role);
        user = userService.save(user);

        Neighborhood neighborhood = neighborhoodService.findById(donorDTO.neighborhoodId())
                .orElseThrow(() -> new EntityNotFoundException("Neighborhood not found"));

        Donor donor = donorMapper.toEntity(donorDTO, user, neighborhood);

        donorService.save(donor);

        return donorMapper.toResponseDTO(donor);
    }

    @Transactional
    @Override
    public NgoResponseDTO registerNgo(NgoRegistrationDTO ngoDTO) {

        if(ngoDTO == null){
            throw new IllegalArgumentException("Ngo registration request cannot be null");
        }

        if(ngoService.existsEmail(ngoDTO.email()) ||
                ngoService.findByTaxId(ngoDTO.taxId()).isPresent() ||
                ngoService.findByLegalPersonalityNumber(ngoDTO.legalPersonalityNumber()).isPresent()){
            throw new ConflictException("Ngo already exists.");
        }

        Neighborhood neighborhood = neighborhoodService.findById(ngoDTO.neighborhoodId())
                .orElseThrow(() -> new EntityNotFoundException("Neighborhood not found"));

        Role role = roleService.findByName("NGO").orElseThrow(() -> new EntityNotFoundException("Role NGO not found"));

        String password = userService.encryptPassword(ngoDTO.password());

        UserSec user = userMapper.toEntity(ngoDTO.email(), password, role);
        user = userService.save(user);

        Ngo ngo = ngoMapper.toEntity(ngoDTO, user, neighborhood);

        ngoService.save(ngo);

        return ngoMapper.toResponseDTO(ngo);
    }
}
