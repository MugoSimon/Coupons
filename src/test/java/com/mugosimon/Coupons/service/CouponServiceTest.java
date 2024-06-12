package com.mugosimon.Coupons.service;

import com.mugosimon.Coupons.model.Coupon;
import com.mugosimon.Coupons.repo.CouponRepository;
import com.mugosimon.Coupons.utility.EntityResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @Mock
    private CouponRepository mockCouponRepository;

    private CouponService couponServiceUnderTest;

    @BeforeEach
    void setUp() {
        couponServiceUnderTest = new CouponService();
        couponServiceUnderTest.couponRepository = mockCouponRepository;
    }

    @Test
    void testCreateCoupon() {
        // Setup
        final Coupon coupon = new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a');

        // Configure CouponRepository.fetchByCode(...).
        final Optional<Coupon> coupon1 = Optional.of(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'));
        when(mockCouponRepository.fetchByCode("code")).thenReturn(coupon1);

        // Run the test
        final EntityResponse result = couponServiceUnderTest.createCoupon(coupon);

        // Verify the results
    }

    @Test
    void testCreateCoupon_CouponRepositoryFetchByCodeReturnsAbsent() {
        // Setup
        final Coupon coupon = new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a');
        when(mockCouponRepository.fetchByCode("code")).thenReturn(Optional.empty());

        // Configure CouponRepository.save(...).
        final Coupon coupon1 = new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a');
        when(mockCouponRepository.save(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'))).thenReturn(coupon1);

        // Run the test
        final EntityResponse result = couponServiceUnderTest.createCoupon(coupon);

        // Verify the results
    }

    @Test
    void testCreateCoupon_CouponRepositorySaveThrowsOptimisticLockingFailureException() {
        // Setup
        final Coupon coupon = new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a');
        when(mockCouponRepository.fetchByCode("code")).thenReturn(Optional.empty());
        when(mockCouponRepository.save(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a')))
                .thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        final EntityResponse result = couponServiceUnderTest.createCoupon(coupon);

        // Verify the results
    }

    @Test
    void testFetchCouponByCode() {
        // Setup
        // Configure CouponRepository.fetchByCode(...).
        final Optional<Coupon> coupon = Optional.of(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'));
        when(mockCouponRepository.fetchByCode("code")).thenReturn(coupon);

        // Run the test
        final EntityResponse result = couponServiceUnderTest.fetchCouponByCode("code");

        // Verify the results
    }

    @Test
    void testFetchCouponByCode_CouponRepositoryReturnsAbsent() {
        // Setup
        when(mockCouponRepository.fetchByCode("code")).thenReturn(Optional.empty());

        // Run the test
        final EntityResponse result = couponServiceUnderTest.fetchCouponByCode("code");

        // Verify the results
    }

    @Test
    void testFetchAllCoupons() {
        // Setup
        // Configure CouponRepository.findAll(...).
        final List<Coupon> coupons = List.of(new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'));
        when(mockCouponRepository.findAll()).thenReturn(coupons);

        // Run the test
        final EntityResponse result = couponServiceUnderTest.fetchAllCoupons();

        // Verify the results
    }

    @Test
    void testFetchAllCoupons_CouponRepositoryReturnsNoItems() {
        // Setup
        when(mockCouponRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final EntityResponse result = couponServiceUnderTest.fetchAllCoupons();

        // Verify the results
    }

    @Test
    void testModifyCoupon() {
        // Setup
        final Coupon updatedCoupon = new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a');

        // Configure CouponRepository.fetchByCode(...).
        final Optional<Coupon> coupon = Optional.of(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'));
        when(mockCouponRepository.fetchByCode("code")).thenReturn(coupon);

        // Configure CouponRepository.save(...).
        final Coupon coupon1 = new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a');
        when(mockCouponRepository.save(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'))).thenReturn(coupon1);

        // Run the test
        final EntityResponse result = couponServiceUnderTest.modifyCoupon(updatedCoupon);

        // Verify the results
    }

    @Test
    void testModifyCoupon_CouponRepositoryFetchByCodeReturnsAbsent() {
        // Setup
        final Coupon updatedCoupon = new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a');
        when(mockCouponRepository.fetchByCode("code")).thenReturn(Optional.empty());

        // Run the test
        final EntityResponse result = couponServiceUnderTest.modifyCoupon(updatedCoupon);

        // Verify the results
    }

    @Test
    void testModifyCoupon_CouponRepositorySaveThrowsOptimisticLockingFailureException() {
        // Setup
        final Coupon updatedCoupon = new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a');

        // Configure CouponRepository.fetchByCode(...).
        final Optional<Coupon> coupon = Optional.of(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'));
        when(mockCouponRepository.fetchByCode("code")).thenReturn(coupon);

        when(mockCouponRepository.save(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a')))
                .thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        final EntityResponse result = couponServiceUnderTest.modifyCoupon(updatedCoupon);

        // Verify the results
    }

    @Test
    void testDeleteCoupon() {
        // Setup
        // Configure CouponRepository.fetchByCode(...).
        final Optional<Coupon> coupon = Optional.of(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'));
        when(mockCouponRepository.fetchByCode("code")).thenReturn(coupon);

        // Run the test
        final EntityResponse result = couponServiceUnderTest.deleteCoupon("code");

        // Verify the results
        verify(mockCouponRepository).delete(new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'));
    }

    @Test
    void testDeleteCoupon_CouponRepositoryFetchByCodeReturnsAbsent() {
        // Setup
        when(mockCouponRepository.fetchByCode("code")).thenReturn(Optional.empty());

        // Run the test
        final EntityResponse result = couponServiceUnderTest.deleteCoupon("code");

        // Verify the results
    }

    @Test
    void testDeleteCoupon_CouponRepositoryDeleteThrowsOptimisticLockingFailureException() {
        // Setup
        // Configure CouponRepository.fetchByCode(...).
        final Optional<Coupon> coupon = Optional.of(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'));
        when(mockCouponRepository.fetchByCode("code")).thenReturn(coupon);

        doThrow(OptimisticLockingFailureException.class).when(mockCouponRepository).delete(
                new Coupon(0L, "code", new BigDecimal("0.00"), "expirationDate", 'a'));

        // Run the test
        final EntityResponse result = couponServiceUnderTest.deleteCoupon("code");

        // Verify the results
    }
}
