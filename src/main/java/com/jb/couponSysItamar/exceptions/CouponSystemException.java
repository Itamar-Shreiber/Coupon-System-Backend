package com.jb.couponSysItamar.exceptions;

public class CouponSystemException extends Exception {

    public CouponSystemException(ErrMsg message) {
        super(message.getMessage());
    }
}
