package com.codexmind.establishment.service;

import com.codexmind.establishment.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;


public class UserService {

    public static User authenticated() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }


}
