package com.tfi.Econexo.service.auth;

import com.tfi.Econexo.dto.auth.admin.UserAdminResponseDTO;
import com.tfi.Econexo.model.enums.RegistrationStatus;

import java.util.List;

public interface AdminUserService {

    List<UserAdminResponseDTO> getAllRegisteredUsers();

    void updateUserStatus(Long userId, RegistrationStatus status);
}
