package com.jb.couponSysItamar.service;

import com.jb.couponSysItamar.repos.CompanyRepository;
import com.jb.couponSysItamar.repos.CouponRepository;
import com.jb.couponSysItamar.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;

//    public abstract boolean login(String email, String password);
}
