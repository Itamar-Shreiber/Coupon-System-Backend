package com.jb.couponSysItamar.service;

import com.jb.couponSysItamar.beans.Category;
import com.jb.couponSysItamar.beans.Company;
import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.beans.Customer;
import com.jb.couponSysItamar.dto.LoginReqDto;
import com.jb.couponSysItamar.dto.LoginResDto;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.login.ClientType;

import java.util.List;
import java.util.UUID;

public interface CustomerService {


//    void purchaseCoupon(int customerId, int couponId) throws CouponSystemException;
    void purchaseCoupon(UUID token, int couponId) throws CouponSystemException;

    List<Coupon> getAllCustomerPurchasedCoupons(int customerId) throws CouponSystemException;
    List<Coupon> getAllCustomerPurchasedCouponsByToken(UUID token) throws CouponSystemException;

    List<Coupon> getAllCustomerPurchasedCouponsByCategory(int customerId, Category category) throws CouponSystemException;

    List<Coupon> getAllCustomerPurchasedCouponsByMaxPrice(int customerId, double maxPrice) throws CouponSystemException;

    Customer getLoginCustomer(int customerId) throws CouponSystemException;

//    boolean login(String email, String password);

    LoginResDto loginDto(LoginReqDto req) throws CouponSystemException;



}