package com.tfi.Econexo.service.impl.payment;

import com.tfi.Econexo.dto.payment.PaymentRequestDTO;
import com.tfi.Econexo.model.donation.MoneyDonation;
import com.tfi.Econexo.model.enums.DonationStatus;
import com.tfi.Econexo.repository.donation.MoneyDonationRepository;
import com.tfi.Econexo.repository.ngo.NgoRepository;
import com.tfi.Econexo.service.donation.DonorService;
import com.tfi.Econexo.service.payment.MoneyDonationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoneyDonationServiceImpl implements MoneyDonationService {

    private final MoneyDonationRepository moneyDonationRepository;
    private final DonorService donorService;
    private final NgoRepository ngoRepository;

    @Override
    @Transactional
    public Long createMoneyDonation(PaymentRequestDTO dto, Optional<String> donorEmail) {
        MoneyDonation donation = new MoneyDonation();
        donation.setAmount(dto.amount());
        donation.setNgo(ngoRepository.findById(dto.ngoId())
                .orElseThrow(() -> new RuntimeException("Ngo not found")));
        donorEmail.flatMap(donorService::findByUserEmail)
                        .ifPresent(donation::setDonor);
        donation.setStatus(DonationStatus.PENDING_PAYMENT);

        return moneyDonationRepository.save(donation).getId();
    }
}
