package com.jb.couponSysItamar.controllers;

import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.dto.CouponPayload;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.exceptions.ErrMsg;
import com.jb.couponSysItamar.beans.ClientType;
import com.jb.couponSysItamar.service.CompanyService;
import com.jb.couponSysItamar.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/companies")
@CrossOrigin(origins = "*")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon( @RequestBody CouponPayload couponPayload, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
            companyService.addCoupon(token, couponPayload);
    }

    @PutMapping("coupons/{couponId}")
    public Coupon updateCoupon(@PathVariable int couponId, @RequestBody CouponPayload couponPayload, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        return companyService.updateCoupon(token, couponId, couponPayload);
    }


    @DeleteMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int couponId, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        companyService.deleteCoupon(token,couponId);
    }


    @GetMapping("coupons")
    public List<Coupon> getAllCompanyCoupons( @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }

        return companyService.getAllCompanyCouponsByToken(token);
    }

}

