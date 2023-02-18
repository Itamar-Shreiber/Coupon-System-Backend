package com.jb.couponSysItamar.repos;

import com.jb.couponSysItamar.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByNameOrEmail(String name, String email);

    boolean existsByEmailAndPassword(String email, String password);

    @Query(value = "SELECT id FROM couponsysitamar.companies WHERE email=? and password=?", nativeQuery = true)

      int findByEmailAndPassword(String email, String password);



}
