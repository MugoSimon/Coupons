package com.mugosimon.Coupons.repo;

import com.mugosimon.Coupons.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends
        JpaRepository<Coupon, Long> {

    @Query("SELECT c FROM Coupon c WHERE c.code = ?1")
    Optional<Coupon> fetchByCode(String code);

}
