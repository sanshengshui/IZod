package com.sanshengshui.action;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordEncoder {
    public static void main(String[] args){
        BCryptPasswordEncoder Encoder = new BCryptPasswordEncoder();
        String hashedPassword = Encoder.encode("jamesmsw");
        System.out.println(hashedPassword);
    }
}
