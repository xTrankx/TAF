package com.playtika.automation.school.test.framework.demo.pojo;

import lombok.Data;
import net.andreinc.mockneat.MockNeat;

@Data
public class User {

    String email;
    String password;
    Integer id;
    String registeredAt;

    public User() {
        this.email = MockNeat.threadLocal().emails().get();
        this.password = MockNeat.threadLocal().passwords().get();
    }
}
