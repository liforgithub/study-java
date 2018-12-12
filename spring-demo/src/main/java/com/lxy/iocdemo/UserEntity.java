
package com.lxy.iocdemo;

import lombok.Data;

@Data
public class UserEntity {

    private String userId;

    private String userName;

    public UserEntity() {
        System.out.println("无参构造函数....");
    }
}
