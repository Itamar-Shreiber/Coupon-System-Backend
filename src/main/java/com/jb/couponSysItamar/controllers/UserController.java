package com.jb.couponSysItamar.controllers;

import com.jb.couponSysItamar.dto.LoginReqDto;
import com.jb.couponSysItamar.dto.LoginResDto;

import com.jb.couponSysItamar.exceptions.CouponSystemException;

import com.jb.couponSysItamar.service.AdminService;
import com.jb.couponSysItamar.service.CompanyService;
import com.jb.couponSysItamar.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by kobis on 29 Dec, 2022
 */
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final CompanyService companyService;
    private final AdminService adminService;
    private final CustomerService customerService;



    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto req) throws CouponSystemException {

        switch (req.getClientType()) {
            case ADMINISTRATOR: {
                return adminService.loginDto(req);
            }
            case COMPANY: {
                return companyService.loginDto(req);
            }
            case CUSTOMER: {
                return customerService.loginDto(req);
            }
        }
        return null;
    }
}