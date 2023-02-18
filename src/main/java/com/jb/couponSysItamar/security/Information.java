package com.jb.couponSysItamar.security;



import com.jb.couponSysItamar.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.usertype.UserType;

import java.time.LocalDateTime;

/**
 * Created by kobis on 29 Dec, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {

    private int id;
    private ClientType type;
    private LocalDateTime time;
}
