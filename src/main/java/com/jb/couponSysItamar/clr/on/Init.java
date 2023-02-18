package com.jb.couponSysItamar.clr.on;


import com.jb.couponSysItamar.beans.Category;
import com.jb.couponSysItamar.beans.Company;
import com.jb.couponSysItamar.beans.Coupon;
import com.jb.couponSysItamar.beans.Customer;
import com.jb.couponSysItamar.repos.CompanyRepository;
import com.jb.couponSysItamar.repos.CouponRepository;
import com.jb.couponSysItamar.repos.CustomerRepository;
import com.jb.couponSysItamar.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Order(1)
public class Init implements CommandLineRunner {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private PrintUtils printUtils;

    @Override
    public void run(String... args) throws Exception {
        Company company1 = Company.builder()
                .name("Coca-Cola")
                .password("1234")
                .email("company1@gmail.com")
                .build();
        Company company2 = Company.builder()
                .name("Migdal")
                .password("1234")
                .email("company2@gmail.com")
                .build();
        Company company3 = Company.builder()
                .name("Mazda")
                .password("1234")
                .email("company3@gmail.com")
                .build();
        Company company4 = Company.builder()
                .name("Hareli")
                .password("1234")
                .email("company4@gmail.com")
                .build();

        Coupon c1 = Coupon.builder()
                .company(company1)
                .category(Category.FOOD)
                .title("1+1")
                .amount(3)
                .price(10)
                .description("Buy one and get one more")
                .startDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .endDate(Date.valueOf(LocalDate.now().plusYears(6)))
                .image("https://picsum.photos/200")
                .build();
        Coupon c2 = Coupon.builder()
                .company(company1)
                .category(Category.ELECTRICITY)
                .title("15%")
                .amount(6)
                .price(30)
                .description("Discount")
                .startDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .endDate(Date.valueOf(LocalDate.now().plusYears(7)))
                .image("https://picsum.photos/200")
                .build();
        Coupon c3 = Coupon.builder()
                .company(company1)
                .category(Category.ELECTRICITY)
                .title("39%")
                .amount(5)
                .price(34)
                .description("Discount")
                .startDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .endDate(Date.valueOf(LocalDate.now().plusYears(2)))
                .image("https://picsum.photos/200")
                .build();
        Coupon c4 = Coupon.builder()
                .company(company2)
                .category(Category.RESTAURANT)
                .title("70%")
                .amount(10)
                .price(60)
                .description("Discount")
                .startDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .image("https://picsum.photos/200")
                .build();
        Coupon c5 = Coupon.builder()
                .company(company2)
                .category(Category.ELECTRICITY)
                .title("5+2")
                .amount(5)
                .price(56)
                .description("Buy and get more")
                .startDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .endDate(Date.valueOf(LocalDate.now().plusYears(2)))
                .image("https://picsum.photos/200")
                .build();
        Coupon c6 = Coupon.builder()
                .company(company3)
                .category(Category.RESTAURANT)
                .title("3+1")
                .amount(0)
                .price(11)
                .description("Buy and get more")
                .startDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .endDate(Date.valueOf(LocalDate.now().plusYears(4)))
                .image("https://picsum.photos/200")
                .build();
        Coupon c7 = Coupon.builder()
                .company(company4)
                .category(Category.ELECTRICITY)
                .title("20%")
                .amount(7)
                .price(100)
                .description("Discount")
                .startDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .endDate(Date.valueOf(LocalDate.now().plusYears(4)))
                .image("https://picsum.photos/200")
                .build();
        Coupon c8 = Coupon.builder()
                .company(company3)
                .category(Category.VACATION)
                .title("40%")
                .amount(1)
                .price(19)
                .description("Discount")
                .startDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .endDate(Date.valueOf(LocalDate.now().plusDays(50)))
                .image("https://picsum.photos/200")
                .build();
        Coupon c9 = Coupon.builder()
                .company(company3)
                .category(Category.VACATION)
                .title("50%")
                .amount(5)
                .price(400)
                .description("Discount")
                .startDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .endDate(Date.valueOf(LocalDate.now().plusYears(4)))
                .image("https://picsum.photos/200")
                .build();
        Coupon c10 = Coupon.builder()
                .company(company3)
                .category(Category.RESTAURANT)
                .title("12+11")
                .amount(10)
                .price(100)
                .description("Buy and get more")
                .startDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .endDate(Date.valueOf(LocalDate.now().plusYears(9)))
                .image("https://picsum.photos/200")
                .build();


        Customer customer1 = Customer.builder()
                .firstName("Adir")
                .lastName("Bidany")
                .email("customer1@gmail.com")
                .password("1234")
                .coupons(List.of(c2, c4, c6))
                .build();
        Customer customer2 = Customer.builder()
                .firstName("Itamar")
                .lastName("Shay")
                .email("customer2@gmail.com")
                .password("6789")
                .coupons(List.of())
                .build();
        Customer customer3 = Customer.builder()
                .firstName("Avi")
                .lastName("Cohen")
                .email("customer3@gmail.com")
                .password("4567")
                .coupons(List.of(c2, c5, c6, c7))
                .build();
        Customer customer4 = Customer.builder()
                .firstName("Kobi")
                .lastName("Bidany")
                .email("customer4@gmail.com")
                .password("3456")
                .coupons(List.of())
                .build();
        Customer customer5 = Customer.builder()
                .firstName("Nadav")
                .lastName("Bidany")
                .email("customer5@gmail.com")
                .password("2345")
                .coupons(List.of(c6, c9, c10))
                .build();

        company1.setCoupons(List.of(c1, c2, c3));
        company2.setCoupons(List.of(c4, c5, c6));
        company3.setCoupons(List.of(c8,c7, c9, c10));

        printUtils.init();
        companyRepository.saveAll(List.of(company1, company2, company3, company4));
        couponRepository.saveAll(List.of(c1, c2, c3, c4, c5, c6, c7,c8, c9, c10));
        customerRepository.saveAll(List.of(customer1, customer2, customer3, customer4, customer5));


        companyRepository.findAll().forEach(System.out::println);
        System.out.println();
        System.out.println();
        System.out.println();
        customerRepository.findAll().forEach(System.out::println);
        System.out.println();
        System.out.println();
        System.out.println();
        couponRepository.findAll().forEach(System.out::println);
        System.out.println();
        System.out.println();
        System.out.println();

    }
}
