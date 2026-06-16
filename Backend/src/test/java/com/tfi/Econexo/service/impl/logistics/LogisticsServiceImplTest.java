package com.tfi.Econexo.service.impl.logistics;

import com.tfi.Econexo.dto.donation.DonationResponseDTO;
import com.tfi.Econexo.mappers.DonationMapper;
import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.donation.Donation;
import com.tfi.Econexo.model.donation.DonationItem;
import com.tfi.Econexo.model.donation.catalog.Product;
import com.tfi.Econexo.model.enums.DonationStatus;
import com.tfi.Econexo.model.logistics.Driver;
import com.tfi.Econexo.model.logistics.Vehicle;
import com.tfi.Econexo.service.donation.DonationService;
import com.tfi.Econexo.service.logistics.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogisticsServiceImplTest {

    @Mock DriverService driverService;
    @Mock DonationService donationService;
    @Mock DonationMapper donationMapper;

    @InjectMocks LogisticsServiceImpl logisticsServiceImpl;

    Driver driver;
    Vehicle vehicle;
    Donation donation;
    DonationItem donationItem;

    @BeforeEach
    void setUp() {
        driver = new Driver();
        driver.setId(1L);
        UserSec user = new UserSec();
        user.setId(1L);
        user.setEmail("driver@example.com");
        driver.setUser(user);

        vehicle = new Vehicle();
        vehicle.setDriver(driver);

        donation = new Donation();
        donation.setId(1L);
        donationItem = new DonationItem();
        Product product = new Product();
        donationItem.setProduct(product);
        donationItem.setDonation(donation);
    }

    @Test
    public void getAvailableTripsNearby_HappyPath() {
        vehicle.setCapacityKg(50);
        vehicle.setHasRefrigeration(false);

        donationItem.setQuantity(20.0);
        donationItem.getProduct().setRequiresRefrigeration(false);
        donationItem.setExpirationDate(LocalDateTime.now().plusDays(1));
        donation.setStatus(DonationStatus.REQUESTED);

        DonationResponseDTO mockDto = DonationResponseDTO.builder().id(1L).build();

        when(driverService.findEntityByEmail(anyString())).thenReturn(Optional.of(driver));
        when(donationService.findAvailableTripsNearby(any(), any(), any())).thenReturn(List.of(donation));
        when(donationMapper.toResponseDTO(donation)).thenReturn(mockDto);

        List<DonationResponseDTO> result = logisticsServiceImpl.getAvailableTripsNearby("driver@example.com", 0.0, 0.0);

        assertEquals(1, result.size());
        assertEquals(donation.getId(), result.get(0).id());
    }

    @Test
    public void getAvailableTripsNearby_CapacityExceeded() {
        vehicle.setCapacityKg(10);

        donationItem.setQuantity(15.0);
        donationItem.getProduct().setRequiresRefrigeration(false);
        donationItem.setExpirationDate(LocalDateTime.now().plusDays(1));
        donation.setStatus(DonationStatus.REQUESTED);

        when(driverService.findEntityByEmail(anyString())).thenReturn(Optional.of(driver));
        when(donationService.findAvailableTripsNearby(any(), any(), any())).thenReturn(Collections.emptyList());

        List<DonationResponseDTO> result = logisticsServiceImpl.getAvailableTripsNearby("driver@example.com", 0.0, 0.0);

        assertEquals(0, result.size());
    }

    @Test
    public void getAvailableTripsNearby_FailedColdChain() {
        vehicle.setHasRefrigeration(false);

        donationItem.getProduct().setRequiresRefrigeration(true);
        donationItem.setExpirationDate(LocalDateTime.now().plusDays(1));
        donation.setStatus(DonationStatus.REQUESTED);

        when(driverService.findEntityByEmail(anyString())).thenReturn(Optional.of(driver));
        when(donationService.findAvailableTripsNearby(any(), any(), any())).thenReturn(Collections.emptyList());

        List<DonationResponseDTO> result = logisticsServiceImpl.getAvailableTripsNearby("driver@example.com", 0.0, 0.0);

        assertEquals(0, result.size());
    }

    @Test
    public void getAvailableTripsNearby_SuccessColdChain(){
        vehicle.setHasRefrigeration(true);

        donationItem.getProduct().setRequiresRefrigeration(true);
        donationItem.setExpirationDate(LocalDateTime.now().plusDays(1));
        donation.setStatus(DonationStatus.REQUESTED);

        when(driverService.findEntityByEmail(anyString())).thenReturn(Optional.of(driver));
        when(donationService.findAvailableTripsNearby(any(), any(), any())).thenReturn(List.of(donation));

        List<DonationResponseDTO> result = logisticsServiceImpl.getAvailableTripsNearby("driver@example.com", 0.0, 0.0);

        assertEquals(1, result.size());
    }

    @Test
    public void getAvailableTripsNearby_ExpiredDateItem(){
        vehicle.setHasRefrigeration(true);

        donationItem.getProduct().setRequiresRefrigeration(true);
        donationItem.setExpirationDate(LocalDateTime.now().minusDays(1));
        donation.setStatus(DonationStatus.REQUESTED);

        when(driverService.findEntityByEmail(anyString())).thenReturn(Optional.of(driver));
        when(donationService.findAvailableTripsNearby(any(), any(), any())).thenReturn(Collections.emptyList());

        List<DonationResponseDTO> result = logisticsServiceImpl.getAvailableTripsNearby("driver@example.com", 0.0, 0.0);

        assertEquals(0, result.size());
    }

    @Test
    public void getAvailableTripsNearby_StatusNotRequested(){

        donation.setStatus(DonationStatus.AVAILABLE);

        when(driverService.findEntityByEmail(anyString())).thenReturn(Optional.of(driver));
        when(donationService.findAvailableTripsNearby(any(), any(), any())).thenReturn(Collections.emptyList());

        List<DonationResponseDTO> result = logisticsServiceImpl.getAvailableTripsNearby("driver@example.com", 0.0, 0.0);

        assertEquals(0, result.size());
    }

}