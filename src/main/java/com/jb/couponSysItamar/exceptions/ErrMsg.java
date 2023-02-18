package com.jb.couponSysItamar.exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {
    ID_NOT_FOUND("\uD83D\uDE25 ID doesn't exist \uD83D\uDE25"),
    COMPANY_NOT_FOUND("\uD83D\uDE25 COMPANY doesn't exist \uD83D\uDE25"),
    CUSTOMER_NOT_FOUND("\uD83D\uDE25 CUSTOMER doesn't exist \uD83D\uDE25"),
    ID_ALREADY_EXIST("\uD83D\uDE25 ID already exist \uD83D\uDE25"),
    COMPANY_NAME_OR_EMAIL_ALREADY_EXIST("\uD83D\uDE25 Company NAME OR EMAIL already exist \uD83D\uDE25"),
    COMPANY_COUPON_TITLE_ALREADY_EXIST("\uD83D\uDE25 Can not enter existing title for the same company \uD83D\uDE25"),
    CATEGORY_DOSENT_EXIST("\uD83D\uDE25 CATEGORY doesn't exist  \uD83D\uDE25"),
    CANNOT_UPDATE_COMPANY_ID("\uD83D\uDE25 Cannot update company ID \uD83D\uDE25"),
    CANNOT_UPDATE_COMPANY_NAME("\uD83D\uDE25 Cannot update company NAME \uD83D\uDE25"),
    CUSTOMER_EMAIL_ALREADY_EXIST("\uD83D\uDE25 Customer EMAIL already exist \uD83D\uDE25"),
    CANNOT_UPDATE_CUSTOMER_ID("\uD83D\uDE25 Cannot update customer ID \uD83D\uDE25"),
    CANNOT_UPDATE_CUSTOMER_PASSWORD("\uD83D\uDE25 Cannot update customer PASSWORD \uD83D\uDE25"),
    EXCEPTION_WRONG_EMIEl_OR_PASSWORD("\uD83D\uDE25 Incorrect email or password \uD83D\uDE25"),
    CANNOT_UPDATE_COUPON_ID("\uD83D\uDE25 Cannot update coupon ID \uD83D\uDE25"),
    COUPON_ALREADY_PURCHASED("\uD83D\uDE25 The coupon has already been purchased \uD83D\uDE25"),
    COUPON_AMOUNT_ZERO("\uD83D\uDE25 No coupon available to purchase \uD83D\uDE25"),
    COUPON_DATE_EXPIRED("\uD83D\uDE25 The coupon has expired \uD83D\uDE25"),
    INVALID_TOKEN("\uD83D\uDE25 Token invalid \uD83D\uDE25");


    private String message;

    ErrMsg(String message) {
        this.message = message;
    }
}
