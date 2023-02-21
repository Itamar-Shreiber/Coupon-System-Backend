package com.jb.couponSysItamar.controllers;
import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.exceptions.ErrMsg;
import com.jb.couponSysItamar.beans.ClientType;
import com.jb.couponSysItamar.service.CustomerService;
import com.jb.couponSysItamar.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private TokenService tokenService;



    @PostMapping("{token}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void purchaseCoupon(@PathVariable UUID token, @PathVariable int couponId, @RequestHeader("Authorization") UUID token2) throws CouponSystemException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        customerService.purchaseCoupon(token, couponId);
    }


    @GetMapping("{token}/coupons")
    public List<Coupon> getAllCustomerPurchasedCoupons(@PathVariable UUID token, @RequestHeader("Authorization") UUID token2) throws CouponSystemException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        return customerService.getAllCustomerPurchasedCouponsByToken(token);
    }


}
