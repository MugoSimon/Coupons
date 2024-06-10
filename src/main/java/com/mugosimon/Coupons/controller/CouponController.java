package com.mugosimon.Coupons.controller;


import com.mugosimon.Coupons.model.Coupon;
import com.mugosimon.Coupons.service.CouponService;
import com.mugosimon.Coupons.utility.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/coupon")
public class CouponController {
    private final String TAG = "CouponController";

    @Autowired
    CouponService couponService;

    // constructor
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    // methods
    @PostMapping("/create")
    public EntityResponse createCoupon(@RequestBody  Coupon coupon) {
        return couponService.createCoupon(coupon);
    }

    @GetMapping("/fetchByCode")
    public EntityResponse fetchCouponByCode(@RequestParam String code) {
        return couponService.fetchCouponByCode(code);
    }

    @GetMapping("/fetchAll")
    public EntityResponse fetchAllCoupons() {
        return couponService.fetchAllCoupons();
    }

    @PutMapping("/modify")
    public EntityResponse modifyCoupon(@RequestBody  Coupon coupon) {
        return couponService.modifyCoupon(coupon);
    }

    @DeleteMapping("/delete")
    public EntityResponse deleteCoupon(@RequestParam String code) {
        return couponService.deleteCoupon(code);
    }

}
