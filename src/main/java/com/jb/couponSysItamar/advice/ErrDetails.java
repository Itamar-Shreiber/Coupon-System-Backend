package com.jb.couponSysItamar.advice;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrDetails {
    private final String key = "couponsys-Itamar";
    private String value;
}
