package com.jb.couponSysItamar.dto;


import com.jb.couponSysItamar.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by kobis on 29 Dec, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReqDto {

    private ClientType clientType;
    private String email;
    private String password;
}

