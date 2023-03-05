package com.jb.couponSysItamar.controllers;

import com.jb.couponSysItamar.beans.Company;
import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.beans.Customer;
import com.jb.couponSysItamar.dto.CompanyUpdatePayload;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.exceptions.ErrMsg;
import com.jb.couponSysItamar.beans.ClientType;
import com.jb.couponSysItamar.service.AdminService;
import com.jb.couponSysItamar.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        adminService.addCompany(company);
    }

    @PutMapping("companies/{companyId}")
    public Company updateCompany(@PathVariable int companyId, @RequestBody CompanyUpdatePayload companyUpdatePayload, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.updateCompany(companyId, companyUpdatePayload);
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyId, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        adminService.deleteCompany(companyId);
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getAllCompanies();
    }

    @GetMapping("companies/{companyId}")
    public Company getSingleCompany(@PathVariable int companyId, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getSingleCompany(companyId);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        adminService.addCustomer(customer);
    }

    @PutMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        adminService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        adminService.deleteCustomer(customerId);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getAllCustomers();
    }

    @GetMapping("customers/{customerId}")
    Customer getSingleCustomer(@PathVariable int customerId, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getSingleCustomer(customerId);
    }
    @GetMapping("coupons")
    public List<Coupon> getAllCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getAllCoupons();
    }
}
