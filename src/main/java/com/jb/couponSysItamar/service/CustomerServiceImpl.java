package com.jb.couponSysItamar.service;

import com.jb.couponSysItamar.beans.*;
import com.jb.couponSysItamar.dto.LoginReqDto;
import com.jb.couponSysItamar.dto.LoginResDto;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.exceptions.ErrMsg;
import com.jb.couponSysItamar.login.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Qualifier
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Autowired
    private TokenService tokenService;
//    @Override
//    public boolean login(String email, String password) {
//        if (customerRepository.existsByEmailAndPassword(email, password)) {
//            System.out.println("customer details: "+email+", password: "+password);
//            return true;
//        }
//        return false;
//    }

    @Override
    public LoginResDto loginDto(LoginReqDto req) throws CouponSystemException {
        if (customerRepository.existsByEmailAndPassword(req.getEmail(), req.getPassword())) {
            int customerId = customerRepository.findByEmailAndPassword(req.getEmail(), req.getPassword());
            User user = new User(customerId,req.getEmail(),req.getPassword(),req.getClientType());
            UUID token = tokenService.addUser(user);
            LoginResDto loginResDto = LoginResDto.builder().token(token).email(req.getEmail()).clientType(req.getClientType()).build();
            return loginResDto;
        }
        throw new CouponSystemException(ErrMsg.EXCEPTION_WRONG_EMIEl_OR_PASSWORD);
    }

//    @Override
//    public void purchaseCoupon(int customerId, int couponId) throws CouponSystemException {
//        Coupon coupon = couponRepository.findById(couponId).orElseThrow(()->new CouponSystemException(ErrMsg.ID_NOT_FOUND));
//        if (coupon.getAmount()==0){
//            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT_ZERO);
//        }
//        if (customerRepository.existsByCustomerIdAndCouponId(customerId,couponId)==1){
//            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_PURCHASED);
//        }
//        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))){
//            throw new CouponSystemException(ErrMsg.COUPON_DATE_EXPIRED);
//        }
//        coupon.setAmount((coupon.getAmount()-1));
//        couponRepository.saveAndFlush(coupon);
//        couponRepository.purchaseCoupon(customerId, couponId);
//
//    }

    @Override
    public void purchaseCoupon(UUID token, int couponId) throws CouponSystemException {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(()->new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        if (coupon.getAmount()==0){
            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT_ZERO);
        }
        int customerId=tokenService.getUserID(token);
        if (customerRepository.existsByCustomerIdAndCouponId(customerId,couponId)==1){
            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_PURCHASED);
        }
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))){
            throw new CouponSystemException(ErrMsg.COUPON_DATE_EXPIRED);
        }
        coupon.setAmount((coupon.getAmount()-1));
        couponRepository.saveAndFlush(coupon);
        couponRepository.purchaseCoupon(customerId, couponId);

    }


    @Override
    public List<Coupon> getAllCustomerPurchasedCoupons(int customerId) throws CouponSystemException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        List<Coupon> customerCoupon = customer.getCoupons();
        return customerCoupon;
    }

    @Override
    public List<Coupon> getAllCustomerPurchasedCouponsByToken(UUID uuid) throws CouponSystemException {
        int customerId= tokenService.getUserID(uuid);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        List<Coupon> customerCoupon = customer.getCoupons();
        return customerCoupon;
    }

    @Override
    public List<Coupon> getAllCustomerPurchasedCouponsByCategory(int customerId, Category category) throws CouponSystemException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        List <Coupon> customerCoupons = customer.getCoupons();
        List <Coupon> customerCouponsByCategory = new ArrayList<>();
        for (Coupon c: customerCoupons) {
            if (c.getCategory().equals(category)) {
                customerCouponsByCategory.add(c);
            }
        }
            return customerCouponsByCategory;
    }


    @Override
    public List<Coupon> getAllCustomerPurchasedCouponsByMaxPrice(int customerId, double maxPrice) throws CouponSystemException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        List <Coupon> customerCoupons = customer.getCoupons();
        List <Coupon> customerCouponsByPrice = new ArrayList<>();
        for (Coupon c: customerCoupons) {
            if (c.getPrice()<maxPrice){
                customerCouponsByPrice.add(c);
            }
        }
        return customerCouponsByPrice;
    }

    @Override
    public Customer getLoginCustomer(int customerId) throws CouponSystemException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        return customer;
    }


}

