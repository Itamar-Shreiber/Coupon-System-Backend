package com.jb.couponSysItamar.jobs;

import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.service.AdminService;
import com.jb.couponSysItamar.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
public class DailyRemovalExpiredCoupons {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AdminService adminService;


    private final int DAILY = (10000);

    @Scheduled(fixedRate = DAILY)
    public void removeExpiredCoupon() throws CouponSystemException {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("deleteExpiredCoupons");
        storedProcedure.execute();
    }
}