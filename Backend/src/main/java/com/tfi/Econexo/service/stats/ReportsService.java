package com.tfi.Econexo.service.stats;

import com.tfi.Econexo.dto.stats.DonorStatsDTO;
import com.tfi.Econexo.dto.stats.DriverStatsDTO;
import com.tfi.Econexo.dto.stats.NgoStatsDTO;

import java.util.Map;

public interface ReportsService {

    Object getStatsByRole(String role, String username);
    NgoStatsDTO getNgoStats(String email);
    DonorStatsDTO getDonorStats(String email);
    DriverStatsDTO getDriverStats(String email);
    Map<String, Object> getAdminStats(String email);

}
