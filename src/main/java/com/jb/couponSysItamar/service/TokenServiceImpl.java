package com.jb.couponSysItamar.service;


import com.jb.couponSysItamar.beans.User;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.exceptions.ErrMsg;
import com.jb.couponSysItamar.beans.ClientType;
import com.jb.couponSysItamar.security.Information;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final Map<UUID, Information> map;

    @Override
    public UUID addUser(User user) {

        UUID token = UUID.randomUUID();
        Information information = Information.builder()
                .id(user.getId())
                .type(user.getType())
                .time(LocalDateTime.now())
                .build();

        map.put(token, information);
        return token;
    }

    @Override
    public void clearTokens() {
        map.values().removeIf(obj->obj.getTime().isBefore(LocalDateTime.now().minusMinutes(30)));
    }

    @Override
    public boolean isValid(UUID token, ClientType type) {
        Information info = map.get(token);


        if(info!=null){
            return info.getType().equals(type);
        }
        return false;
    }

    @Override
    public int getUserID(UUID token) throws CouponSystemException {
        System.out.println(map);
        Information info = map.get(token);
        if(info!=null){
            System.out.println(info);
            return info.getId();
        }
        throw new CouponSystemException(ErrMsg.INVALID_TOKEN);
    }
}
