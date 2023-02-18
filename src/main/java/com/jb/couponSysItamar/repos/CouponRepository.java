package com.jb.couponSysItamar.repos;

import com.jb.couponSysItamar.beans.Category;
import com.jb.couponSysItamar.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findAll();

    boolean existsByCompanyIdAndTitle(int companyId, String title);

    boolean existsByCategory(Category category);

    List<Coupon> findByCompanyId(int companyId);

    boolean existsByCompanyId(int companyId);

    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);

    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double price);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO customers_coupons (customer_id, coupons_id) VALUES (?, ?)", nativeQuery = true)
    void purchaseCoupon(int customerId, int couponId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customers_coupons WHERE coupons_id = ?", nativeQuery = true)
    void deletePurchaseCoupon(int couponId);
//
//    @Modifying
//    @Transactional
//    @Query(value = "set sql_safe_updates=0;" +
//                   "delete from coupons where end_date<current_date();" +
//                   "set sql_safe_updates=1;", nativeQuery = true)
//    void deleteExpCoupon();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customers_coupons WHERE customer_id = ?", nativeQuery = true)
    void deletePurchaseCouponByCustomer(int customerId);


    @Query(value = "SELECT coupons_id FROM customers_coupons", nativeQuery = true)
    List<Integer> getAllPurchasedCoupons();


}
