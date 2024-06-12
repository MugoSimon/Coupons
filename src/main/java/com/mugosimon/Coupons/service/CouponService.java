package com.mugosimon.Coupons.service;


import com.mugosimon.Coupons.model.Coupon;
import com.mugosimon.Coupons.repo.CouponRepository;
import com.mugosimon.Coupons.utility.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CouponService {
    private final String TAG = "CouponService: ";
    @Autowired
    CouponRepository couponRepository;

    // Create Coupon
    public EntityResponse createCoupon(Coupon coupon) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            log.info("====> create coupon <====");
            Optional<Coupon> existingCoupon = couponRepository.fetchByCode(coupon.getCode());

            if (existingCoupon.isPresent()) {
                log.error(TAG + "Coupon already exists");
                entityResponse.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
                entityResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return entityResponse;
            }

            Coupon savedCoupon = couponRepository.save(coupon);
            entityResponse.setEntity(savedCoupon);
            log.info("====> coupon created");
            entityResponse.setMessage(HttpStatus.CREATED.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.CREATED.value());
            return entityResponse;

        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(">+++++> error while creating coupon <++++++<: " + exception.getLocalizedMessage());
            entityResponse.setMessage(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return entityResponse;
        }

    }

    // Fetch Coupon By Code
    public EntityResponse fetchCouponByCode(String code) {
        EntityResponse entityResponse = new EntityResponse<>();

        try {
            log.info(TAG + "fetch Coupon By Code");
            Optional<Coupon> couponOptional = couponRepository.fetchByCode(code);

            if (couponOptional.isPresent()) {
                log.info("====> coupon fetched successfully: " + couponOptional.get().getCode());
                entityResponse.setEntity(couponOptional.get());
                entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
                entityResponse.setStatusCode(HttpStatus.OK.value());
            } else {
                entityResponse.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            }

            return entityResponse;
        } catch (Exception ex) {
            log.error(">+++++> error while fetching coupon <++++++<: {}", ex.getLocalizedMessage());
            ex.printStackTrace();

            entityResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return entityResponse;
        }
    }

    // Fetch All Coupons
    public EntityResponse fetchAllCoupons() {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            log.info(TAG + "fetch all coupons");

            List<Coupon> couponList = couponRepository.findAll();
            if (couponList.isEmpty()) {
                log.info("====> coupons fetched successfully");
                entityResponse.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return entityResponse;
            }
            entityResponse.setEntity(couponList);
            entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.OK.value());
            return entityResponse;
        } catch (Exception ex) {
            log.error(">+++++> error while fetching coupons <++++++<: {}", ex.getLocalizedMessage());
            ex.printStackTrace();
            entityResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return entityResponse;
        }
    }

    // Modify Coupon
    public EntityResponse modifyCoupon(Coupon updatedCoupon) {
        EntityResponse entityResponse = new EntityResponse<>();

        try {
            log.info(TAG + "modify coupon");
            Optional<Coupon> couponOptional = couponRepository.fetchByCode(updatedCoupon.getCode());

            if (couponOptional.isPresent()) {
                Coupon existingCoupon = couponOptional.get();

                existingCoupon.setDiscount(updatedCoupon.getDiscount());
                existingCoupon.setExpirationDate(updatedCoupon.getExpirationDate());

                Coupon savedCoupon = couponRepository.save(existingCoupon);
                entityResponse.setEntity(savedCoupon);
                log.info("====> coupons modified successfully: " + savedCoupon.getCode());
                entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
                entityResponse.setStatusCode(HttpStatus.OK.value());

            } else {
                entityResponse.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            }

            return entityResponse;
        } catch (Exception ex) {
            log.error(">+++++> error while updating coupon <++++++<: {}", ex.getLocalizedMessage());
            ex.printStackTrace();

            entityResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return entityResponse;
        }
    }

    // Delete Coupon
    public EntityResponse deleteCoupon(String code) {
        EntityResponse entityResponse = new EntityResponse<>();

        try {
            log.info(TAG + "delete Coupon");
            Optional<Coupon> couponOptional = couponRepository.fetchByCode(code);

            if (couponOptional.isPresent()) {
                couponRepository.delete(couponOptional.get());
                log.info("====> coupons deleted successfully");
                entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
                entityResponse.setStatusCode(HttpStatus.OK.value());
            } else {
                log.warn("====> coupons not found");
                entityResponse.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            }

            return entityResponse;
        } catch (Exception ex) {
            log.error(">+++++> error while deleting coupon <++++++<: {}", ex.getLocalizedMessage());
            ex.printStackTrace();

            entityResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return entityResponse;
        }
    }

}
