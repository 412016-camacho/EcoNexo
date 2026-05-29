package com.tfi.Econexo.service.auth;

import com.tfi.Econexo.dto.auth.donor.DonorRegistrationDTO;
import com.tfi.Econexo.dto.auth.donor.DonorResponseDTO;
import com.tfi.Econexo.dto.auth.ngo.NgoRegistrationDTO;
import com.tfi.Econexo.dto.auth.ngo.NgoResponseDTO;

public interface AuthService {

    DonorResponseDTO registerDonor(DonorRegistrationDTO donorRegistrationDTO);
    NgoResponseDTO registerNgo(NgoRegistrationDTO ngoRegistrationDTO);
}
