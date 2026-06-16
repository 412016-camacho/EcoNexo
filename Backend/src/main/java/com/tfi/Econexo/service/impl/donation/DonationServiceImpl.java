package com.tfi.Econexo.service.impl.donation;

import com.tfi.Econexo.dto.donation.DonationRequestDTO;
import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.dto.donation.DonationSummaryResponseDTO;
import com.tfi.Econexo.exception.ConflictException;
import com.tfi.Econexo.mappers.DonationMapper;
import com.tfi.Econexo.model.donation.Donation;
import com.tfi.Econexo.model.donation.DonationItem;
import com.tfi.Econexo.model.donation.catalog.Product;
import com.tfi.Econexo.model.donation.catalog.UnitOfMeasure;
import com.tfi.Econexo.model.donation.donor.Donor;
import com.tfi.Econexo.model.enums.DonationStatus;
import com.tfi.Econexo.model.ngo.Ngo;
import com.tfi.Econexo.repository.donation.DonationRepository;
import com.tfi.Econexo.repository.donation.catalog.ProductRepository;
import com.tfi.Econexo.repository.donation.catalog.UnitOfMeasureRepository;
import com.tfi.Econexo.repository.ngo.NgoRepository;
import com.tfi.Econexo.service.donation.DonationService;
import com.tfi.Econexo.service.donation.DonorService;
import com.tfi.Econexo.service.impl.GeocodingService;
import com.tfi.Econexo.utils.GeometryUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final GeocodingService geocodingService;
    private final DonorService donorService;
    private final ProductRepository productRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final NgoRepository ngoRepository;

    private final DonationMapper donationMapper;


    @Transactional
    @Override
    public DonationResponseDTO donate(DonationRequestDTO donationRequestDTO) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Donor donor = donorService.findByUserEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Donor not found"));

        //Consumir el GeocodingService combinando la calle y número para obtener las coordenadas.
        if(donor.getLocation() == null){
            String fullAddress = donor.getStreet() + " " + donor.getStreetNumber() + ", " + donor.getNeighborhood().getName() + ", " + donor.getNeighborhood().getCity().getName();
            GeocodingService.Coordinates coords = geocodingService.getCoordinates(fullAddress);

            if(coords != null){
                Point locationPoint = GeometryUtils.createPoint(coords.lng(), coords.lat());
                donor.setLocation(locationPoint);
                donorService.save(donor);
            }
        }

        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setPickupStartTime(donationRequestDTO.pickupStartTime());
        donation.setPickupEndTime(donationRequestDTO.pickupEndTime());
        donation.setStatus(DonationStatus.AVAILABLE);
        List<DonationItem> items = donationRequestDTO.items().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            UnitOfMeasure uom =unitOfMeasureRepository.findById(itemDto.unitOfMeasureId())
                    .orElseThrow(() -> new EntityNotFoundException("Unit of measure not found"));

            DonationItem item = donationMapper.toItemEntity(itemDto);
            item.setDonation(donation);
            item.setProduct(product);
            item.setUnitOfMeasure(uom);
            item.setDescription(itemDto.description());

            return  item;
        }).toList();

        donation.setDonationItems(items);
        Donation savedDonation = donationRepository.save(donation);

        return donationMapper.toResponseDTO(savedDonation);
    }

    @Override
    public List<DonationSummaryResponseDTO> getAvailableDonationsSummary() {
        List<Donation> donations = donationRepository.findByStatusAvailableAndNotExpired();

        return donations.stream()
                .map(donationMapper::toSummaryResponseDTO)
                .toList();
    }

    @Transactional
    @Override
    public void requestDonation(Long donationId, String ngoEmail) {
        Ngo ngo = ngoRepository.findByUser_Email(ngoEmail)
                .orElseThrow(() -> new EntityNotFoundException("Ngo not found"));

        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new EntityNotFoundException("Donation not found"));

        if(donation.getStatus() != DonationStatus.AVAILABLE){
            throw new ConflictException("This donation was already requested by another NGO or it's not available anymore.");
        }

        donation.setStatus(DonationStatus.REQUESTED);
        donation.setNgo(ngo);
        donationRepository.save(donation);
    }

    @Override
    public List<Donation> findAvailableTripsNearby(Point driverLocation, Long driverId, DonationStatus status) {
        return donationRepository.findAvailableTripsNearby(driverLocation, driverId, status);
    }
}
