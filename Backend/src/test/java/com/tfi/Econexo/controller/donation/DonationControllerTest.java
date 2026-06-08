package com.tfi.Econexo.controller.donation;

import com.tfi.Econexo.config.AuditorAwareImpl;
import com.tfi.Econexo.dto.donation.DonationSummaryResponseDTO;
import com.tfi.Econexo.model.auth.Role;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.security.config.SecurityConfig;
import com.tfi.Econexo.service.auth.BlacklistedTokenService;
import com.tfi.Econexo.service.donation.CatalogService;
import com.tfi.Econexo.service.donation.DonationService;
import com.tfi.Econexo.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DonationController.class)
@AutoConfigureMockMvc
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AuditorAwareImpl.class))
@Import(SecurityConfig.class)
class DonationControllerTest {

    @Autowired MockMvc mockMvc;
    @MockitoBean DonationService donationService;
    @MockitoBean CatalogService catalogService;
    @MockitoBean JwtUtils jwtUtils;
    @MockitoBean UserDetailsService userDetailsService;
    @MockitoBean BlacklistedTokenService blacklistedTokenService;

    UserSec user;
    Role role;

    @BeforeEach
    void setup() {
        role = new Role();
        role.setRole("DONOR");

        user = new UserSec();
        user.setRolesList(Set.of(role));
    }

    @Test
    @WithMockUser(roles = "NGO")
    void getAvailableDonations_Success() throws Exception {

        when(donationService.getAvailableDonationsSummary()).thenReturn(List.of(new DonationSummaryResponseDTO(
                1L, "Business", LocalDateTime.now().plusDays(2),true , List.of())));

        mockMvc.perform(
                        get("/api/v1/donations/available"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "DONOR")
    void getAvailableDonations_Forbidden_NoNgoRole() throws Exception {

        mockMvc.perform(
                        get("/api/v1/donations/available"))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAvailableDonations_Unauthorized_NoTokenProvided ()throws Exception {

        mockMvc.perform(
                        get("/api/v1/donations/available"))
                .andExpect(status().isUnauthorized()
                );
    }
}