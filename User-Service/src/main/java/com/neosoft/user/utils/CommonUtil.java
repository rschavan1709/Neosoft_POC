package com.neosoft.user.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class CommonUtil {

    public static User getLoggedinUser() throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (principal instanceof User) {
            String username = ((User) principal).getUsername();
        } else {
            throw new Exception("Unauthorized User");
        }
        return (User) principal;
    }
    
}
