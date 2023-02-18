package com.jb.couponSysItamar.service;

import com.jb.couponSysItamar.beans.User;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.login.ClientType;



import java.util.UUID;

/**
 * Created by kobis on 29 Dec, 2022
 */
public interface TokenService {

    UUID addUser(User user);
    void clearTokens();
    boolean isValid(UUID token, ClientType type);
    int getUserID(UUID token) throws CouponSystemException;
}
