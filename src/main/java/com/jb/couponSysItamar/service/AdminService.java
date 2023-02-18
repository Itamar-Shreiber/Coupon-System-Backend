package com.jb.couponSysItamar.service;

import com.jb.couponSysItamar.beans.Company;
import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.beans.Customer;
import com.jb.couponSysItamar.dto.CompanyPayload;
import com.jb.couponSysItamar.dto.CompanyUpdatePayload;
import com.jb.couponSysItamar.dto.LoginReqDto;
import com.jb.couponSysItamar.dto.LoginResDto;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.login.ClientType;

import java.util.List;

public interface AdminService {

    void addCompany(Company company) throws CouponSystemException;

    void updateCompany(int companyId, Company company) throws CouponSystemException;
    Company updateCompany(int companyId, CompanyUpdatePayload companyUpdatePayload) throws CouponSystemException;

    void deleteCompany(int companyId) throws CouponSystemException;

    List<Company> getAllCompanies();

    List<Coupon> getAllCoupons();

    List<Integer> getAllPurchasedCoupons();

    Company getSingleCompany(int companyId) throws CouponSystemException;



    void addCustomer(Customer customer) throws CouponSystemException;

    void updateCustomer(int customerId, Customer customer) throws CouponSystemException;

    void deleteCustomer(int customerId) throws CouponSystemException;

    List<Customer> getAllCustomers();

    Customer getSingleCustomer(int customerId) throws CouponSystemException;

//    boolean login(String email, String password);
    LoginResDto loginDto(LoginReqDto req) throws CouponSystemException;
//    void deleteExpCoupon() throws CouponSystemException;


}
