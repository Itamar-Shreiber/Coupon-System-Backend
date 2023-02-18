//package com.jb.couponSysItamar.login;
//
//import com.jb.couponSysItamar.exceptions.CouponSystemException;
//import com.jb.couponSysItamar.exceptions.ErrMsg;
//import com.jb.couponSysItamar.service.AdminService;
//import com.jb.couponSysItamar.service.ClientService;
//import com.jb.couponSysItamar.service.CompanyService;
//import com.jb.couponSysItamar.service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//
//@Service
//
//public class LoginManager {
//    @Autowired
//    private ClientService clientService;
//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private CompanyService companyService;
//    @Autowired
//    private CustomerService customerService;
//
//    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
//
//        switch (clientType) {
//            case ADMINISTRATOR: {
//                if (adminService.login(email, password)) {
//                    System.out.println("Admin logged in successfully");
//                    return (ClientService) adminService;
//                }
//            }
//            case COMPANY: {
//                if (companyService.login(email, password)) {
//                    System.out.println("Company logged in successfully");
//                    return (ClientService) companyService;
//                } else throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND);
//            }
//            case CUSTOMER:
//                if (customerService.login(email, password)) {
//                    System.out.println("Customer logged in successfully");
//                    return (ClientService) customerService;
//                } else throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND);
//        }
//        return null;
//    }
//
//}
