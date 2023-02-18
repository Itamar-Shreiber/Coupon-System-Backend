package com.jb.couponSysItamar.jobs;


import com.jb.couponSysItamar.beans.Category;
import com.jb.couponSysItamar.beans.Company;
import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.repos.CouponRepository;
import com.jb.couponSysItamar.service.AdminService;
import com.jb.couponSysItamar.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(9)
public class DailyRemovalExpiredCouponsTest implements CommandLineRunner {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CouponRepository couponRepository;


    @Override
    public void run(String... args) throws Exception {
        Company company1 = adminService.getSingleCompany(1);
        Coupon C = Coupon.builder()
                .company(company1)
                .category(Category.RESTAURANT)
                .title("coupon expired")
                .amount(3)
                .price(100)
                .description("Buy and get more")
                .startDate(Date.valueOf(LocalDate.now().minusYears(23)))
                .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .image("https://picsum.photos/200")
                .build();
        company1.setCoupons(List.of(C));
        couponRepository.save(C);
    }
}
