package com.tfi.Econexo.service.auth;

import com.tfi.Econexo.dto.DonorRegistrationDTO;
import com.tfi.Econexo.dto.DonorResponseDTO;
import com.tfi.Econexo.dto.NgoRegistrationDTO;
import com.tfi.Econexo.dto.NgoResponseDTO;

public interface AuthService {

    DonorResponseDTO registerDonor(DonorRegistrationDTO donorRegistrationDTO);
    NgoResponseDTO registerNgo(NgoRegistrationDTO ngoRegistrationDTO);
}
