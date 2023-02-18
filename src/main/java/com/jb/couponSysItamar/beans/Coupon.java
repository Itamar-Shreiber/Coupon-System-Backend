package com.jb.couponSysItamar.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jb.couponSysItamar.dto.CouponPayload;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Company company;

    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(length = 40, nullable = false)
    private String title;
    @Column(length = 40, nullable = false)
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    @Column(length = 100, nullable = false)
    private String image;

    public Coupon(CouponPayload couponPayload) {
        this.category = couponPayload.getCategory();
        this.title = couponPayload.getTitle();
        this.description = couponPayload.getDescription();
        this.startDate = couponPayload.getStartDate();
        this.endDate = couponPayload.getEndDate();
        this.amount = couponPayload.getAmount();
        this.price = couponPayload.getPrice();
        this.image = couponPayload.getImage();
    }

}
