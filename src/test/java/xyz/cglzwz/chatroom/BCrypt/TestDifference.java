package xyz.cglzwz.chatroom.BCrypt;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestDifference {
    @Test
    public void test() {
        BCryptPasswordEncoder encoder1 = new BCryptPasswordEncoder();
        PasswordEncoder encoder2 = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        System.out.println(encoder1.encode("mima"));
        System.out.println(encoder2.encode("mima"));
    }
}
