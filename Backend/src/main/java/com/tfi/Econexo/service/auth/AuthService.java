package com.tfi.Econexo.service.auth;

import com.tfi.Econexo.dto.auth.DonorRegistrationDTO;
import com.tfi.Econexo.dto.auth.DonorResponseDTO;
import com.tfi.Econexo.dto.auth.NgoRegistrationDTO;
import com.tfi.Econexo.dto.auth.NgoResponseDTO;

public interface AuthService {

    DonorResponseDTO registerDonor(DonorRegistrationDTO donorRegistrationDTO);
    NgoResponseDTO registerNgo(NgoRegistrationDTO ngoRegistrationDTO);
}
