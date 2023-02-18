package com.jb.couponSysItamar.repos;

import com.jb.couponSysItamar.beans.Category;
import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    @Transactional
    @Query(value = "SELECT EXISTS (SELECT * FROM customers_coupons WHERE `customer_id`=? and `coupons_id`=?) as res", nativeQuery = true)
    int existsByCustomerIdAndCouponId(int customerId, int couponId);

    @Query(value = "SELECT id FROM couponsysitamar.customers WHERE email=? and password=?", nativeQuery = true)

    int findByEmailAndPassword(String email, String password);
}
