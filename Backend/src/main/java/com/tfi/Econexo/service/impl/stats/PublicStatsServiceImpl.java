package com.tfi.Econexo.service.impl.stats;

import com.tfi.Econexo.dto.stats.LandingStatsDTO;
import com.tfi.Econexo.repository.donation.DonationRepository;
import com.tfi.Econexo.repository.donation.DonorRepository;
import com.tfi.Econexo.repository.donation.MoneyDonationRepository;
import com.tfi.Econexo.repository.logistics.DriverRepository;
import com.tfi.Econexo.repository.ngo.NgoRepository;
import com.tfi.Econexo.service.stats.PublicStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PublicStatsServiceImpl implements PublicStatsService {

    private final DonationRepository donationRepository;
    private final MoneyDonationRepository moneyDonationRepository;
    private final NgoRepository ngoRepository;
    private final DriverRepository driverRepository;
    private final DonorRepository donorRepository;

    @Override
    public LandingStatsDTO getLandingStats() {
        Double totalKilos = donationRepository.sumAllDeliveredKilos();
        Long totalDeliveries = donationRepository.countAllDeliveredDonations();
        Double totalMoney = moneyDonationRepository.sumAllDonatedAmount();

        return new LandingStatsDTO(
                totalKilos != null ? BigDecimal.valueOf(totalKilos) : BigDecimal.ZERO,
                totalDeliveries != null ? totalDeliveries : 0L,
                totalMoney != null ? BigDecimal.valueOf(totalMoney) : BigDecimal.ZERO,
                ngoRepository.countActiveNgos(),
                donorRepository.countActiveDonors(),
                driverRepository.countActiveDrivers()
        );
    }
}
