package com.jb.couponSysItamar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CompanyUpdatePayload {

    private String email;
    private String password;
}
