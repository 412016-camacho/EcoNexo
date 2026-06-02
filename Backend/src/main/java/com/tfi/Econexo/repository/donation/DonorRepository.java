package com.tfi.Econexo.repository.donation;

import com.tfi.Econexo.model.donation.donor.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {

    Boolean existsByTaxId(String taxId);

    Boolean existsByUser_Email(String email);

    Optional<Donor> findByUser_Email(String email);

}
