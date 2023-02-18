package com.jb.couponSysItamar.service;

import com.jb.couponSysItamar.beans.Category;
import com.jb.couponSysItamar.beans.Company;
import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.dto.CouponPayload;
import com.jb.couponSysItamar.dto.LoginReqDto;
import com.jb.couponSysItamar.dto.LoginResDto;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.login.ClientType;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

//    void addCoupon(int companyId, CouponPayload couponPayload) throws CouponSystemException;
    void addCoupon(UUID token, CouponPayload couponPayload) throws CouponSystemException;
//    void addCoupon(Coupon coupon) throws CouponSystemException;

//    void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException;
    Coupon updateCoupon(UUID token, int couponId, CouponPayload couponPayload) throws CouponSystemException;


//    void deleteCoupon(int couponId) throws CouponSystemException;
    void deleteCoupon(UUID token, int couponId) throws CouponSystemException;

//    List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSystemException;

    Coupon getSingleCoupon(int couponId) throws CouponSystemException;
    List<Coupon> getAllCompanyCouponsByToken(UUID uuid) throws CouponSystemException;

    List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws CouponSystemException;

    List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws CouponSystemException;

    Company getLoginCompany(int companyId) throws CouponSystemException;

//    boolean login(String email, String password);
    LoginResDto loginDto(LoginReqDto req) throws CouponSystemException;


}

