package com.jb.couponSysItamar.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jb.couponSysItamar.dto.CompanyPayload;
import com.jb.couponSysItamar.dto.CompanyUpdatePayload;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Company {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(length = 40, nullable = false)
    private String name;
    @Column (length =40,nullable = false)
    private  String email;
    @Column (length =40,nullable = false)
    private String password;


    @OneToMany (mappedBy = "company",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @Singular
    @ToString.Exclude
    @JsonIgnore
    private List <Coupon> coupons;

  

    public Company(CompanyUpdatePayload companyUpdatePayload) {
        this.email = companyUpdatePayload.getEmail();
        this.password = companyUpdatePayload.getPassword();
    }

}
