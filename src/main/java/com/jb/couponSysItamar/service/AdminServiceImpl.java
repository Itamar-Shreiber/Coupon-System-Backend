package com.jb.couponSysItamar.service;

import com.jb.couponSysItamar.beans.Company;
import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.beans.Customer;
import com.jb.couponSysItamar.dto.CompanyUpdatePayload;
import com.jb.couponSysItamar.dto.LoginReqDto;
import com.jb.couponSysItamar.dto.LoginResDto;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.exceptions.ErrMsg;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class AdminServiceImpl extends ClientService implements AdminService {

    @Override
    public LoginResDto loginDto(LoginReqDto req) throws CouponSystemException {
        if (req.getEmail().equals("admin@admin.com") && req.getPassword().equals("admin")) {
            LoginResDto loginResDto = new LoginResDto(UUID.randomUUID(), req.getEmail(), req.getClientType());
            return loginResDto;
        }
        throw new CouponSystemException(ErrMsg.EXCEPTION_WRONG_EMIEl_OR_PASSWORD);
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {

        if (companyRepository.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_OR_EMAIL_ALREADY_EXIST);
        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        Company c0 = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        if (c0.getId() != company.getId()) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COMPANY_ID);
        }
        if (!c0.getName().equals(company.getName())) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COMPANY_NAME);
        }
        companyRepository.saveAndFlush(company);
    }

    public Company updateCompany(int companyId, CompanyUpdatePayload companyUpdatePayload) throws CouponSystemException {
        Company company = new Company(companyUpdatePayload);
        company.setId(companyId);
        String name = companyRepository.findById(companyId).get().getName();
        company.setName(name);
        System.out.println(company);

        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        Company c0 = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        if (c0.getId() != company.getId()) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COMPANY_ID);
        }
        if (!c0.getName().equals(company.getName())) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COMPANY_NAME);
        }
        return companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        Company company0 = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        List<Coupon> list = company0.getCoupons();
        for (Coupon coupon0 : list) {
            couponRepository.deletePurchaseCoupon(coupon0.getId());
        }
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public List<Integer> getAllPurchasedCoupons() {
        return couponRepository.getAllPurchasedCoupons();
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if (customerRepository.existsById(customer.getId())) {
            throw new CouponSystemException(ErrMsg.ID_ALREADY_EXIST);
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_ALREADY_EXIST);
        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        Customer c0 = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        System.out.println(c0.getId());
        customer.setId(c0.getId());
        if (c0.getId() != customer.getId()) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_CUSTOMER_ID);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        if (!customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        couponRepository.deletePurchaseCouponByCustomer(customerId);
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
    }

}
