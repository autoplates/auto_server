package org.auto.plate;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlateApplicationTests {

    @Test
    public void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 10; i++) {
            System.out.println(encoder.encode("123"));
        }
    }

}
