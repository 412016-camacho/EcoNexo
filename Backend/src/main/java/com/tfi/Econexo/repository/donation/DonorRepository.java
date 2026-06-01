package com.tfi.Econexo.repository.donation;

import com.tfi.Econexo.model.donation.donor.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {

    Boolean existsByTaxId(String taxId);

    Boolean existsByUser_Email(String email);

}
