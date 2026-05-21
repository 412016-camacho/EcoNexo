package com.tfi.Econexo.service.auth;

import com.tfi.Econexo.dto.DonorRegistrationDTO;
import com.tfi.Econexo.dto.DonorResponseDTO;

public interface AuthService {

    DonorResponseDTO registerDonor(DonorRegistrationDTO donorRegistrationDTO);
}
