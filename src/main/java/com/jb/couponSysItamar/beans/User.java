package com.jb.couponSysItamar.beans;

import com.jb.couponSysItamar.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Entity
//@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
//
//    @Id
//    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
//    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
//    private List<Task> tasks;

    private ClientType type;
}
